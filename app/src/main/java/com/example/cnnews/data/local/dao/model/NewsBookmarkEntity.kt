package com.example.cnnews.data.local.dao.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "news_bookmark" , indices = [Index(value = ["title"], unique = true)])
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
) : Serializable