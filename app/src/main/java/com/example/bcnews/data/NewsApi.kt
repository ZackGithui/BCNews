package com.example.bcnews.data

import com.example.bcnews.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category:String="general",
        @Query("language") language:String="en",
        @Query("apiKey")apiKey: String=Constants.API_KEY

    ):NewsResponse


}