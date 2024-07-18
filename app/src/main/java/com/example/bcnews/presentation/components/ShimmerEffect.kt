package com.example.bcnews.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffect(

) {
    val shimmerColors= listOf(
        Color.LightGray.copy(0.6f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.6f)
    )
    val transition= rememberInfiniteTransition()

    val animateTransition=transition.animateFloat(
        initialValue = 0f,
        targetValue =1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse

        )
    )

    val brush=Brush.linearGradient(
        colors= shimmerColors,
        start = Offset.Zero,
        end = Offset(x = animateTransition.value,y=animateTransition.value)

    )
    ShimmerObject(brush = brush)

}

@Composable
fun ShimmerObject(brush: Brush) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically){
        Spacer(modifier = Modifier
            .size(60.dp)
            .clip(
                RoundedCornerShape(5.dp)
            )
            .background(brush))

        Spacer(modifier = Modifier.width(10.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier
                .fillMaxWidth(fraction = 0.87f)
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush)
                )
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth(fraction = 0.67f)
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush))

        }


    }

}

@Preview(showBackground = true)
@Composable
private fun ShimmerPrev() {


    ShimmerObject(brush =Brush.linearGradient(colors =listOf(
        Color.LightGray.copy(0.6f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.6f)
    )))

}