package com.example.cnnews.data.network

import com.example.cnnews.domain.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
    val api: SimpleRetro by lazy {
        retrofit.create(SimpleRetro::class.java)
    }
}