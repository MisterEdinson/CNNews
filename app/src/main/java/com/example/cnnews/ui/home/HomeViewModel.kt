package com.example.cnnews.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity
import com.example.cnnews.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val dataListNet: MutableLiveData<List<NewsBookmarkEntity>> = MutableLiveData()
    val dataListLoc: MutableLiveData<List<NewsBookmarkEntity>> = MutableLiveData()

    fun getNews() {
        viewModelScope.launch {
            val responce = repository.getNews()
            dataListNet.value = responce
        }
    }

    fun searchNewsNet(search: String) {
        viewModelScope.launch {
            val responce = repository.searchNews(search)
            dataListNet.value = responce
        }
    }

    fun addFavorite(favorite: NewsBookmarkEntity) {
        viewModelScope.launch {
            repository.addFavorite(favorite)
        }
    }

    fun delFavorite(delete: NewsBookmarkEntity) {
        viewModelScope.launch {
            repository.delFavorite(delete)
        }
    }

    fun getAllFavorite() {
        viewModelScope.launch {
            val responce = repository.getAllFavorite()
            dataListLoc.value = responce
        }
    }

    fun searchNewsLocal(searchFavorite: String) {
        viewModelScope.launch {
            val responce = repository.searchFavorite(searchFavorite)
            dataListLoc.value = responce
        }
    }
}