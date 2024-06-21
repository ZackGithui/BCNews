package com.example.bcnews.data

import com.example.bcnews.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category:String,
        @Query("language") language:String="us",
        @Query("apiKey")apiKey: String = Constants.API_KEY

    ):NewsResponse


}