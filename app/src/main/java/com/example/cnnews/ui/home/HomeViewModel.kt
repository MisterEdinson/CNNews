package com.example.cnnews.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cnnews.data.network.model.ResponseCountry
import com.example.cnnews.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val dataList: MutableLiveData<ResponseCountry> = MutableLiveData()

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            val responce = repository.getNews()
            dataList.value = responce
        }
    }
}