package com.example.bcnews.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bcnews.domain.model.ArticleData

@Composable
fun BottomSheet(
    articleData: ArticleData,

) {
    Surface(modifier = Modifier.padding(10.dp)) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            ImageHolder(imageUrl = articleData.urlToImage?:"")
            Text(text = articleData.title?:"", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = articleData.publishedAt?:"")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = articleData.author?:"")

            Spacer(modifier = Modifier.height(12.dp))
            Text(text = articleData.description?:"")
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = articleData.content?:"")
            
            

        }

    }

}