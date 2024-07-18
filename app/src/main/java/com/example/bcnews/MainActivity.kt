package com.example.bcnews


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bcnews.presentation.NewsScreen

import com.example.bcnews.presentation.NewsViewModel
import com.example.bcnews.presentation.components.SearchScreen
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
            SearchScreen(navController = navController)
        }

    }


}