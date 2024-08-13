package com.example.bcnews


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bcnews.presentation.NewsScreen

import com.example.bcnews.presentation.search.SearchScreen
import com.example.bcnews.presentation.navigation.AppScreen
import com.example.bcnews.ui.theme.BCNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BCNewsTheme {
                App()

            }
        }
    }
}

@Composable
fun App() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.NewsScreen.route) {
      composable(AppScreen.NewsScreen.route){
          NewsScreen(navController = navController)
      }

        composable(AppScreen.SearchScreen.route){
            SearchScreen(navController = navController, onCloseButtonClicked = {}, onSearchIconClicked = {} )
        }

    }


}