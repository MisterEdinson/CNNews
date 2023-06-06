package com.example.cnnews.data.local.dao

import androidx.room.*
import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity

@Dao
interface NewsBookmarkDao {
    @Query("SELECT * FROM news_bookmark ORDER BY publishedAt DESC")
    suspend fun getAllNewsBookmark(): List<NewsBookmarkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewBookmark(insert: NewsBookmarkEntity)

    @Delete
    suspend fun delNewBookmark(newsBookmark: NewsBookmarkEntity)

    @Query("DELETE FROM news_bookmark")
    suspend fun delAllNewsBookmark()
}