package com.example.cnnews.domain.utils

interface MapperNewsHostToNewsDao<ArticlesItem, NewsBookmarkEntity> {
    fun mappingHostToDao(responce: ArticlesItem?): NewsBookmarkEntity
}