package com.example.cnnews.di

import android.content.Context
import androidx.room.Room
import com.example.cnnews.data.local.NewsLocalDB
import com.example.cnnews.data.local.dao.NewsBookmarkDao
import com.example.cnnews.data.network.SimpleRetro
import com.example.cnnews.domain.utils.Constants
import com.example.cnnews.domain.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun baseUrl() = BASE_URL

    @Provides
    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okhttpClient() = OkHttpClient.Builder().addInterceptor {
        val reques = it.request().newBuilder().addHeader("X-Auth-Token", Constants.BASE_URL).build()
        it.proceed(reques)
    }.addInterceptor(logging()).build()

    @Provides
    fun gson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(): SimpleRetro =
        Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(gson())
            .client(okhttpClient())
            .build()
            .create(SimpleRetro::class.java)

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            NewsLocalDB::class.java,
            "news_bookmark"
        ).build()

    @Provides
    fun provideNewsBookmarkDao(appDataBaseNews: NewsLocalDB): NewsBookmarkDao {
        return appDataBaseNews.newsBookmarkDao()
    }
}