package com.example.bcnews.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bcnews.data.Article

@Composable
fun NewsScreen(
               viewModel: NewsViewModel= hiltViewModel()) {
    val state=viewModel.state.collectAsState().value
    when{
        state.isLoading ->{
            Text(text = "Loading...")
        }
        state.error != null ->{
            Text(text = state.error.toString())
        }
        else->{
            LazyColumn (modifier = Modifier
                // .background(MaterialTheme.colorScheme.error)
                .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                items(state.data) { news ->

                    Text(text = news.title)

                }

            }

        }
    }


}


@Preview
@Composable
 fun HomeScreenPrev() {
     NewsScreen()

}