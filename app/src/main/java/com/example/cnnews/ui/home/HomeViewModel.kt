package com.example.cnnews.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cnnews.data.network.model.ResponseCountry
import com.example.cnnews.domain.Repository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val repo = Repository()
    var dataList: MutableLiveData<ResponseCountry> = MutableLiveData()

    fun getNews() {
        viewModelScope.launch {
            dataList.value = repo.getNews()
        }
    }

}