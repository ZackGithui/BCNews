package com.example.bcnews.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serial

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
)