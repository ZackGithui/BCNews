package com.example.bcnews.data

import android.os.Message
import com.example.bcnews.domain.model.ArticleData
import com.example.bcnews.domain.repository.NewsRepository
import com.example.bcnews.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val api:NewsApi):NewsRepository {
    override suspend fun getTopHeadlines(category: String): Flow<Resource<List<ArticleData>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getTopHeadlines(category).articles.map { it.toArticleData() }
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}