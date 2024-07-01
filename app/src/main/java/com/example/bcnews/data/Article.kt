package com.example.bcnews.data


import com.example.bcnews.domain.model.ArticleData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "author")
    val author: String?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "publishedAt")
    val publishedAt: String?,
    @Json(name = "source")
    val source: Source?,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String?,
    @Json(name = "urlToImage")
    val urlToImage: String?
)


fun Article.toArticleData():ArticleData{
    return ArticleData(
        title=title,
        url=url,
        description=description,
        urlToImage=urlToImage,
        content=content
    )
}


