package com.example.bcnews.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bcnews.R
import com.example.bcnews.presentation.components.ImageHolder
import com.example.bcnews.presentation.navigation.AppScreen


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
    onCloseButtonClicked: () -> Unit,
    onSearchIconClicked: () -> Unit
) {
    val state = viewModel.state.collectAsState().value.text
    val result=viewModel.state.collectAsState().value.result

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        // horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { navController.navigate(AppScreen.NewsScreen.route) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(34.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.search),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.primary
                )

            )

        }

        var text by remember {
            mutableStateOf("")
        }
        val focusRequester = remember {
            FocusRequester()
        }
        var keyboardController = LocalSoftwareKeyboardController.current
        var focusManager = LocalFocusManager.current


        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .focusRequester(focusRequester)
                    .padding(horizontal = 10.dp),

                shape = RoundedCornerShape(10.dp),
                value = state,
                onValueChange = { newValue ->
                    viewModel.onEvent(SearchEvents.OnSearchQueryChanged(query = newValue))


                },
                placeholder = { Text(text = "Search") },
                singleLine = true,
                leadingIcon = {
                    Icons.Default.Search
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search")

                },
                trailingIcon = {
                    if (text.isNotEmpty()) {

                        IconButton(onClick = {
                            if (text.isNotEmpty())
                                text = ""
                            else onCloseButtonClicked()

                        }) {
                            Icons.Default.Close
                            Icon(imageVector = Icons.Default.Close, contentDescription = "close")

                        }
                    }

                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search

                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.onEvent(SearchEvents.OnSearchQueryChanged(query = state))
                        onSearchIconClicked()
                        keyboardController?.hide()
                        focusManager.clearFocus()

                    }
                ),
            )
            LazyColumn {
                items(result ?: emptyList()){ list->
                     Row(horizontalArrangement = Arrangement.SpaceAround){
                        ImageHolder(imageUrl = list.urlToImage?:"")
                         Column {
                             Text(text = list.title?:"")
                             Spacer(modifier = Modifier.height(10.dp))
                             Text(text = list.content?:"")
                             Spacer(modifier = Modifier.height(10.dp))
                             Text(text = list.publishedAt?:"")
                         }
                    }


                }
            }


        }


    }


}
