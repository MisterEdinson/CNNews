package com.example.cnnews.ui.home

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.cnnews.R
import com.example.cnnews.databinding.ActivityMainBinding
import com.example.cnnews.ui.bokmark.BookmarkFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.bottomNavScreen.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.bookmarkFragment -> replaceFragment(BookmarkFragment())
                else -> {
                    Log.e("error", "=======")
                }
            }
            true
        }
        val etSearchNews = findViewById<EditText>(R.id.etSearchNews)
        imgSearchClose.setOnClickListener {
            etSearchNews.setText("")
        }
        etSearchNews.addTextChangedListener {
            searchEditText(it)
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransiting = fragmentManager.beginTransaction()
        fragmentTransiting.replace(R.id.fragmentContainerView,fragment)
        fragmentTransiting.commit()
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
}


