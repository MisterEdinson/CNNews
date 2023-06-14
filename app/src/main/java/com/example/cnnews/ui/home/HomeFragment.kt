package com.example.cnnews.ui.home

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
import com.example.cnnews.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var newsAdapter: HomeNewsAdapter? = null
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = activity?.findViewById<EditText>(R.id.etSearchNews)
        val clearSearch = activity?.findViewById<ImageView>(R.id.imgSearchClose)

        initAdapter()
        viewModel.getNews()
        clearSearch?.setOnClickListener {
            editText?.setText("")
        }
        editText?.addTextChangedListener {
            searchNet(it)
        }
        viewModel.dataListNet.observe(viewLifecycleOwner) {
            newsAdapter?.setList(it)
        }
    }

    private fun initAdapter() {
        newsAdapter = HomeNewsAdapter(
            { favorite -> addFavorite(favorite) },
            { details -> openDetails(details) }
        )
        binding.rvHome.apply {
            adapter = newsAdapter
        }
    }

    private fun addFavorite(favorite: NewsBookmarkEntity) {
        viewModel.addFavorite(favorite)
        Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()
    }

    private fun openDetails(article: NewsBookmarkEntity) {
        val bundle = bundleOf("article" to article)
        findNavController().navigate(R.id.action_homeFragment_to_articleFragment,bundle)
    }

    private fun searchNet(edit: Editable?) {
        job?.cancel()
        job = MainScope().launch {
            edit.let {
                if (it.toString().isEmpty()) {
                    viewModel.getNews()
                } else {
                    viewModel.searchNewsNet(it.toString())
                }
            }
        }
    }
}