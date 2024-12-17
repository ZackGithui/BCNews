package com.example.bcnews.presentation.search

import com.example.bcnews.presentation.NewsEvents

sealed interface SearchEvents {
    data class OnSearchQueryChanged(val query:String): SearchEvents


}
