package com.example.cnnews.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cnnews.R
import com.example.cnnews.data.network.model.ArticlesItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private var newsAdapter: HomeNewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        viewModel.dataList.observe(this) {
            it.articles.let { list ->
                newsAdapter?.setList(list as List<ArticlesItem>)
            }
        }
    }

    private fun initAdapter() {
        newsAdapter = HomeNewsAdapter()
        rvNewsHome.apply {
            adapter = newsAdapter
        }
    }
}


