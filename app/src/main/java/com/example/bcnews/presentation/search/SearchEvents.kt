package com.example.bcnews.presentation.search

import com.example.bcnews.presentation.NewsEvents

sealed class SearchEvents {
    data class onSearchQueryChanged(val query:String): SearchEvents()


}