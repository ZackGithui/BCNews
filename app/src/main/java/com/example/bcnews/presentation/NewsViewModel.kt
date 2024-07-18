package com.example.bcnews.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bcnews.domain.model.ArticleData
import com.example.bcnews.domain.repository.NewsRepository
import com.example.bcnews.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _state = MutableStateFlow(NewsState())
    val state: StateFlow<NewsState> = _state.asStateFlow()




    fun onEvent(events: NewsEvents) {
        when (events) {
            is NewsEvents.CategoryChanged -> {
               _state.value=_state.value.copy(category = events.category)

                getNewsArticles(category = _state.value.category)
                viewModelScope.launch {
                    repository.getTopHeadlines(category = _state.value.category)
                }

            }
            is NewsEvents.NewsClicked -> {
                _state.value=_state.value.copy(selectedArticle = events.articleData)
            }
            NewsEvents.SearchIconClicked -> {

            }

        }
    }
    init {
        getNewsArticles(category = _state.value.category)
    }


    private  fun getNewsArticles(category:String) {
        viewModelScope.launch {
            repository.getTopHeadlines(category = category).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            data = result.data ?: emptyList(),
                            isLoading = false,
                            error = null,

                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message ?: "Unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }
        }
    }
}

data class NewsState(
    val data: List<ArticleData> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false,
    val category: String="sports",
    val selectedArticle:ArticleData?=null
)