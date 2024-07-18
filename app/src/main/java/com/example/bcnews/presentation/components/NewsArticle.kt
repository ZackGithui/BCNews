package com.example.bcnews.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bcnews.domain.model.ArticleData

@Composable
fun NewsArticle(
    articleData: ArticleData,
    onCardClicked:(ArticleData)->Unit

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()


            .clickable { onCardClicked(articleData) },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
        ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),

            ) {
            ImageHolder(imageUrl = articleData.urlToImage?:"")
            Spacer(modifier = Modifier.width(20.dp))

            Text(text = articleData.title?:"",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))

        }


    }
}