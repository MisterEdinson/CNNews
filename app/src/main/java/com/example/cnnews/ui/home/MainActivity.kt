package com.example.cnnews.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cnnews.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getNews()
        viewModel.dataList.observe(this) {
            Log.e("=====", it.status.toString())
        }
        setContentView(R.layout.activity_main)
    }
}


