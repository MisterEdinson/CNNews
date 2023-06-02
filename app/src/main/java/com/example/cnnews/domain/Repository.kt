package com.example.cnnews.domain

import com.example.cnnews.data.network.RetrofitInstance
import com.example.cnnews.data.network.model.ResponseCountry

class Repository{

    suspend fun getNews() : ResponseCountry{
        return RetrofitInstance.api.getNewsCountryDefault()
    }
}