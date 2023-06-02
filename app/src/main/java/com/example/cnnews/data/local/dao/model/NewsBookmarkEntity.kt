package com.example.cnnews.data.local.dao.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "news_bookmark")
@Parcelize
data class NewsBookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var source: String? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var content: String? = null,
    var publishedAt: String? = null,
)