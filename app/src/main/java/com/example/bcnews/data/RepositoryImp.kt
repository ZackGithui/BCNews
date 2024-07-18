package com.example.bcnews.data

import android.util.Log
import com.example.bcnews.domain.model.ArticleData
import com.example.bcnews.domain.repository.NewsRepository
import com.example.bcnews.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val api: NewsApi) : NewsRepository {

    companion object {
        private const val TAG = "RepositoryImp"
    }

    override suspend fun getTopHeadlines(category:String): Flow<Resource<List<ArticleData>>> = flow {
        emit(Resource.Loading())
        try {
           // Log.d(TAG, "Fetching top headlines for category: $category")
            val response = api.getTopHeadlines(category=category)
            Log.d(TAG, "Response: $response")
            val articles = response.articles.map { it.toArticleData() }
            emit(Resource.Success(articles))
            Log.d(TAG, "Successfully fetched ${articles.size} articles")
        } catch (e: HttpException) {
            Log.e(TAG, "HttpException: ${e.message}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.e(TAG, "IOException: ${e.message}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
            emit(Resource.Error("An unknown error occurred"))
        }
    }
}
