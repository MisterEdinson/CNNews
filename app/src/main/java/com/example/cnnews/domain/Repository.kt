package com.example.cnnews.domain

import com.example.cnnews.data.network.SimpleRetro
import com.example.cnnews.data.network.model.ResponseCountry
import javax.inject.Inject

class Repository @Inject constructor(
    private val simpleRetro: SimpleRetro
){
    suspend fun getNews() : ResponseCountry{
        return simpleRetro.getNewsCountryDefault()
    }
    suspend fun searchNews(search:String) : ResponseCountry{
        return simpleRetro.getSearchNewsCountry(search)
    }
}