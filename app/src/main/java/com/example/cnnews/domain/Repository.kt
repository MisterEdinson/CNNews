package com.example.cnnews.domain

import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity
import com.example.cnnews.data.network.SimpleRetro
import com.example.cnnews.data.network.model.ArticlesItem
import com.example.cnnews.data.network.model.ResponseCountry
import com.example.cnnews.domain.usecase.ModelMappingUseCase
import javax.inject.Inject

class Repository @Inject constructor(
    private val simpleRetro: SimpleRetro
){
    suspend fun getNews() : List<NewsBookmarkEntity>{
        val news = simpleRetro.getNewsCountryDefault()
        val getAllHostNews = ModelMappingUseCase().converterModel(news)
        return getAllHostNews
    }
    suspend fun searchNews(search:String) : List<NewsBookmarkEntity>{
        val search = simpleRetro.getSearchNewsCountry(search)
        val searchHostNews = ModelMappingUseCase().converterModel(search)
        return searchHostNews
    }
}