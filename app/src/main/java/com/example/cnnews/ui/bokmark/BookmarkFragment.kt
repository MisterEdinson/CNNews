package com.example.cnnews.ui.bokmark

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cnnews.R
import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity
import com.example.cnnews.databinding.FragmentBookmarkBinding
import com.example.cnnews.ui.home.HomeNewsAdapter
import com.example.cnnews.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var viewModel: HomeViewModel
    private var newsAdapter: HomeNewsAdapter? = null
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding = FragmentBookmarkBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teSearch = activity?.findViewById<EditText>(R.id.etSearchNews)
        val btClose = activity?.findViewById<ImageView>(R.id.imgSearchClose)

        initAdapter()

        viewModel.getAllFavorite()
        btClose?.setOnClickListener {
            teSearch?.setText("")
        }
        teSearch?.addTextChangedListener {
            search(it)
        }

        viewModel.dataListLoc.observe(viewLifecycleOwner) {
            newsAdapter?.setList(it)
        }
    }

    private fun initAdapter() {
        newsAdapter = HomeNewsAdapter(
            { favorite -> delFavorite(favorite) },
            { datails -> openDetails(datails) }
        )
        binding.rvBookmarks.apply {
            adapter = newsAdapter
        }
    }

    private fun delFavorite(favorite: NewsBookmarkEntity) {
        viewModel.delFavorite(favorite)
        Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()
        viewModel.getAllFavorite()
    }

    private fun openDetails(article: NewsBookmarkEntity) {
        val bundle = bundleOf("article" to article)
        findNavController().navigate(R.id.action_bookmarkFragment_to_articleFragment, bundle)
    }

    private fun search(edit: Editable?) {
        job?.cancel()
        job = MainScope().launch {
            edit.let {
                if (it.toString().isEmpty()) {
                    viewModel.getAllFavorite()
                } else {
                    viewModel.searchNewsLocal(it.toString())
                }
            }
        }
    }
}