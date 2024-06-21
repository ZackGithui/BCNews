package com.example.bcnews.domain.repository

import com.example.bcnews.data.Article
import com.example.bcnews.domain.model.ArticleData
import com.example.bcnews.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopHeadlines(category:String): Flow<Resource<List<Article>>>
}