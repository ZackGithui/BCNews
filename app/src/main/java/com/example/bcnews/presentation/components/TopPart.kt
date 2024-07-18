package com.example.bcnews.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopPart(onButtonClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(start = 2.dp, top = 25.dp, bottom = 0.dp)
    ) {
        TopAppBar(
            title = {
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Top Headlines News",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 27.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "News from all over the world",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 19.sp
                        ),
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }
            },
            actions = {
                IconButton(onClick = { onButtonClick() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "null",
                        Modifier.size(40.dp),
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.primary,
                actionIconContentColor = MaterialTheme.colorScheme.primary,
            ),
        )
        Spacer(modifier = Modifier.height(8.dp))

    }
}
