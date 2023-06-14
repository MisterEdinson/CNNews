package com.example.cnnews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cnnews.data.local.dao.NewsBookmarkDao
import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity

@Database(entities = [NewsBookmarkEntity::class], version = 1, exportSchema = false)
abstract class NewsLocalDB : RoomDatabase() {
    abstract fun newsBookmarkDao() : NewsBookmarkDao
}