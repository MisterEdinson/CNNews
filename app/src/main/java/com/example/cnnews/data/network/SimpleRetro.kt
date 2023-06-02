package com.example.cnnews.data.network

import com.example.cnnews.data.network.model.ResponseCountry
import retrofit2.http.GET

interface SimpleRetro {
    @GET("top-headlines?country=ua&apiKey=fafe7f309c354670bfb0afc5f5ce0204")
    suspend fun getNewsCountryDefault() : ResponseCountry

}