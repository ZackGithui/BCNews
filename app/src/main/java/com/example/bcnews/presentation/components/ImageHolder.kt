package com.example.bcnews.presentation.components

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bcnews.R

@Composable
fun ImageHolder(
    imageUrl: String
) {
    Box (
        contentAlignment = Alignment.Center){


    Card(

        modifier = Modifier
            .height(80.dp)
            .width(80.dp)
            .padding(start = 8.dp)
            ,





    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.drawable.icon)  // Placeholder image while loading
                .error(R.drawable.icon)        // Error image if loading fails
                .build(),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(RoundedCornerShape(4.dp)),
            alignment = Alignment.Center
        )
    }}
}
