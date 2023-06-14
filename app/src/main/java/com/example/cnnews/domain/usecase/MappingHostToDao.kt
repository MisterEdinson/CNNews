package com.example.cnnews.domain.usecase

import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity
import com.example.cnnews.data.network.model.ArticlesItem
import com.example.cnnews.domain.utils.MapperNewsHostToNewsDao

class MappingHostToDao : MapperNewsHostToNewsDao<ArticlesItem, NewsBookmarkEntity> {
    override fun mappingHostToDao(responce: ArticlesItem?): NewsBookmarkEntity {
        return NewsBookmarkEntity(
            id = 0,
            source = responce?.source.toString(),
            author = responce?.author.toString(),
            title = responce?.title.toString(),
            description = responce?.description.toString(),
            url = responce?.url.toString(),
            urlToImage = responce?.urlToImage.toString(),
            content = responce?.content.toString(),
            publishedAt = responce?.publishedAt.toString()
        )
    }


}