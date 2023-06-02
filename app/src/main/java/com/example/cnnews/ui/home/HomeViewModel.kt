package com.example.cnnews.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cnnews.data.network.model.ResponseCountry
import com.example.cnnews.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var dataList: MutableLiveData<ResponseCountry> = MutableLiveData()

}