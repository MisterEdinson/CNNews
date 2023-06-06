package com.example.cnnews.data.network

import com.example.cnnews.data.network.model.ArticlesItem
import com.example.cnnews.data.network.model.ResponseCountry
import com.example.cnnews.domain.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleRetro {
    @GET("top-headlines")
    suspend fun getNewsCountryDefault(
        @Query("country") country : String = "ua",
        @Query("apikey") apikey : String = API_KEY
    ) : ResponseCountry

    @GET("everything")
    suspend fun getSearchNewsCountry(
        @Query("q") search:String,
        @Query("apikey") apikey: String = API_KEY
    ) : ResponseCountry
}