package com.example.cnnews.domain.usecase

import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity
import com.example.cnnews.data.network.model.ArticlesItem
import com.example.cnnews.data.network.model.ResponseCountry

class ModelMappingUseCase {
    fun converterModel(responce: ResponseCountry): List<NewsBookmarkEntity> {
        val news: List<ArticlesItem?>? = responce.articles
        val mapper = MappingHostToDao()
        val mapping: List<NewsBookmarkEntity> =
            news?.mapNotNull { itemNews ->
                itemNews.let {
                    mapper.mappingHostToDao(it)
                }
            } ?: emptyList()
        return mapping
    }
}