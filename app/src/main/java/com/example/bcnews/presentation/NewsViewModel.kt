package com.example.bcnews.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bcnews.data.Article
import com.example.bcnews.domain.model.ArticleData
import com.example.bcnews.domain.repository.NewsRepository
import com.example.bcnews.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository):ViewModel() {

   private var _state = MutableStateFlow(NewsState())
    val state:StateFlow<NewsState> = _state.asStateFlow()

    init {
        getNewsArticles(category = "general")
    }

    private fun getNewsArticles(category:String){
        viewModelScope.launch {
             repository.getTopHeadlines(category = category).collect{ result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = NewsState(result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _state.value= NewsState( error = result.message?:"unexpected error occurred")

                    }
                    is Resource.Loading ->{
                        _state.value=NewsState(isLoading = true)
                    }
                }


            }


        }
    }




}


data class NewsState(
    val data :List<ArticleData> = emptyList(),
    val error: String?=null,
    val isLoading:Boolean=false

)