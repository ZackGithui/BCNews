package com.example.bcnews.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bcnews.domain.model.ArticleData
import com.example.bcnews.domain.repository.NewsRepository
import com.example.bcnews.presentation.NewsState
import com.example.bcnews.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState())
    val state get() = _state.asStateFlow()

    //Handling UI changes
    fun onEvent(event: SearchEvents) {
        when (event) {
            is SearchEvents.OnSearchQueryChanged -> {
                _state.value = _state.value.copy(
                    text = event.query,





                )
                searchedNews(query = event.query)


            }
        }

    }
    //Handling filters
   private fun searchedNews(query:String){
        viewModelScope.launch {
            if(_state.value.text.isEmpty()){

            }
            val news = repository.searchNews(query = _state.value.text).data
         _state.value=_state.value.copy(
             result = news?.filter{ it.title?.lowercase()!!.contains(_state.value.text.lowercase()) }
         )


        }
    }






    data class NewsState(

        val text: String = "",
        val result:List<ArticleData> ?= emptyList(),
        val error:String ?=""
    )


}