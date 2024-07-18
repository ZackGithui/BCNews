package com.example.bcnews.presentation.navigation

sealed class AppScreen(val route:String){
    data object NewsScreen:AppScreen( "Home Screen")
    data object SearchScreen:AppScreen("Search Screen")
}