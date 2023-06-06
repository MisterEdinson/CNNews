package com.example.cnnews.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cnnews.data.local.dao.NewsBookmarkDao
import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity
import com.example.cnnews.data.network.model.ResponseCountry
import com.example.cnnews.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val dataList: MutableLiveData<List<NewsBookmarkEntity>> = MutableLiveData()

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            val responce = repository.getNews()
            dataList.value = responce
        }
    }

    fun searchNews(search: String) {
        viewModelScope.launch {
            val responce = repository.searchNews(search)
            dataList.value = responce
        }
    }

    fun addFavorite(favorite : NewsBookmarkEntity){
        viewModelScope.launch {
            repository.addFavorite(favorite)
        }
    }
}