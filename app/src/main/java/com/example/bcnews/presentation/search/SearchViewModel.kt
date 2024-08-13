package com.example.bcnews.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bcnews.data.RepositoryImp
import com.example.bcnews.domain.model.ArticleData
import com.example.bcnews.domain.repository.NewsRepository
import com.example.bcnews.presentation.NewsEvents
import com.example.bcnews.presentation.NewsState
import com.example.bcnews.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel(){

    private val _state = mutableStateOf(NewsState())
    val state = _state

    fun onEvent(event: SearchEvents){
        when(event){
            is SearchEvents.onSearchQueryChanged ->{
                _state.value=_state.value.copy(
                    onSearchQueryChanged =event.query


                )
                searchNews(query = _state.value.onSearchQueryChanged)


            }
        }

    }
    init {
        searchNews(query = _state.value.onSearchQueryChanged)
    }


     fun searchNews(query:String){
        if (query.isEmpty()){
            return
        }
        viewModelScope.launch {
            repository.searchNews(query = query).collect{result->
                when(result){
                    is Resource.Loading ->{
                        _state.value=_state.value.copy(isLoading = true)
                    }
                    is Resource.Success->{
                        _state.value=_state.value.copy(
                            isLoading = false,
                            data = result.data?: emptyList(),
                            error = null
                        )
                    }
                    is Resource.Error->{
                        _state.value=_state.value.copy(
                            isLoading = false,
                            error = result.message?:"unexpected error occurred!"
                        )
                    }
                }


            }
        }


    }

    data class NewsState(
        var data: List<ArticleData> = emptyList(),
        val error: String? = null,
        val isLoading: Boolean = false,
        val category: String="sports",
        val selectedArticle: ArticleData?=null,
        val text:String="",
        val active:Boolean=false,
        val onSearchQueryChanged:String="",
        val onSearchScreenVisible:Boolean=false
    )



}