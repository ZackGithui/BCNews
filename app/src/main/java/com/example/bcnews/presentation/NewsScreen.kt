package com.example.bcnews.presentation

import CategoryTab
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bcnews.presentation.components.BottomSheet
import com.example.bcnews.presentation.components.NewsArticle
import com.example.bcnews.presentation.search.SearchScreen
import com.example.bcnews.presentation.components.ShimmerEffect
import com.example.bcnews.presentation.components.TopPart
import com.example.bcnews.presentation.navigation.AppScreen
import com.example.bcnews.presentation.search.SearchViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
               navController: NavHostController) {
    val state by viewModel.state.collectAsState()

    val categories = listOf("General", "Business", "Health", "Science", "Sports", "Technology", "Entertainment")
    val pagerState = rememberPagerState(pageCount = { categories.size })
    val coroutineScope = rememberCoroutineScope()
    val sheetState= rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var shouldBottomSheetShow= remember {
        mutableStateOf(false)
    }
    var isSearchScreenVisible:Boolean=false
    val focusRequester= remember {
        FocusRequester()
    }



    if (shouldBottomSheetShow.value){
        ModalBottomSheet(
            onDismissRequest = { shouldBottomSheetShow.value=true
                               },
            sheetState=sheetState,
            content = {
                state.selectedArticle?.let {
                    BottomSheet(articleData = it)

                }


            }

            )


    }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            viewModel.onEvent(NewsEvents.CategoryChanged(categories[page]))
        }
    }




    Scaffold(topBar = { TopPart(onButtonClick = {
        navController.navigate(AppScreen.SearchScreen.route)
        isSearchScreenVisible =true
    viewModel.state.value.data= emptyList()
    }) }) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues)

        ) {
            CategoryTab(
                pagerState = pagerState,
                categories = categories,
                onTabClicked = { index ->


                    coroutineScope.launch { pagerState.animateScrollToPage(index)
                        viewModel.onEvent(NewsEvents.CategoryChanged(categories[index]))



                    }

                },

            )
            if(state.isLoading== true) {
                Column {
                    repeat(10) {
                        ShimmerEffect()

                    }
                }
            }
            else{


                Crossfade(targetState = isSearchScreenVisible) { isVisible ->
                    if (isVisible) {
                        SearchScreen(

                            navController = navController,

                            onCloseButtonClicked = { /*TODO*/ },
                            onSearchIconClicked = {
                                coroutineScope.launch {
                                    delay(500)
                                    focusRequester.requestFocus()
                                }

                            },
                            )

                              LazyColumn {
                                  items(state.data){article->
                                      NewsArticle(
                                          articleData = article,
                                          onCardClicked = {})



                                  }
                              }







                    } else {

                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 2.dp),
                                contentPadding = PaddingValues(16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(state.data) { article ->
                                    NewsArticle(
                                        articleData = article,
                                        onCardClicked = {
                                            viewModel.onEvent(NewsEvents.NewsClicked(articleData = it))
                                            shouldBottomSheetShow.value = true

                                        }
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }
}}

@Preview
@Composable
fun HomeScreenPrev() {
    val context= LocalContext.current
    NewsScreen(navController = NavHostController(context = context))
}
