package com.example.cnnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity
import com.example.cnnews.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var newsAdapter: HomeNewsAdapter? = null

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
        initAdapter()
        viewModel.dataList.observe(viewLifecycleOwner) {
            newsAdapter?.setList(it)
        }
    }

    private fun initAdapter() {
        newsAdapter = HomeNewsAdapter { favorite -> addFavorite(favorite) }
        binding.rvHome.apply {
            adapter = newsAdapter
        }
    }

    private fun addFavorite(favorite : NewsBookmarkEntity){
        viewModel.addFavorite(favorite)
    }
}