package com.example.cnnews.domain.utils

import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity
import com.example.cnnews.data.network.model.ArticlesItem

interface MapperNewsHostToNewsDao < ArticlesItem, NewsBookmarkEntity> {
    fun mappingHostToDao(responce : ArticlesItem?) : NewsBookmarkEntity
}