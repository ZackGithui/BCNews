package com.example.bcnews.presentation

import com.example.bcnews.domain.model.ArticleData

sealed class NewsEvents {
    data class NewsClicked(val articleData: ArticleData):NewsEvents()
    data class CategoryChanged(val category:String):NewsEvents()
    object SearchIconClicked:NewsEvents()

}