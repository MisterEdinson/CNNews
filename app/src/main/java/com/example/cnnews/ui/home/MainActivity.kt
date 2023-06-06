package com.example.cnnews.ui.home

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.cnnews.R
import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity
import com.example.cnnews.data.network.model.ArticlesItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private var newsAdapter: HomeNewsAdapter? = null
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchText = findViewById<EditText>(R.id.etSearchNews)
        initAdapter()
        viewModel.dataList.observe(this) {
             newsAdapter?.setList(it)
        }
        imgSearchClose.setOnClickListener {
            etSearchNews.setText("")
        }
        searchText.addTextChangedListener {
            searchEditText(it)
        }
    }

    private fun searchEditText(edit: Editable?) {
        job?.cancel()
        job = MainScope().launch {
            edit.let {
                if (it.toString().isEmpty()) {
                    viewModel.getNews()
                } else {
                    viewModel.searchNews(it.toString())
                }
            }
        }
    }

    private fun initAdapter() {
        newsAdapter = HomeNewsAdapter(
            { favorite -> addFavorite(favorite)}
        )
        rvNewsHome.apply {
            adapter = newsAdapter
        }
    }

    private fun addFavorite(favorite : NewsBookmarkEntity){
        viewModel.addFavorite(favorite)
    }
}


