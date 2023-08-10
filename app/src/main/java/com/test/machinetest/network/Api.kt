package com.test.machinetest.network

import com.test.machinetest.model.NewsResponse
import com.test.machinetest.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("category") category: String?,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("country") language: String = "us"
    ): Response<NewsResponse>

}