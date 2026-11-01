package com.example.firstcomposeproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme

/**
 * Задание 6. Векторный круг из res/drawable/circle.xml.
 */

// Функция 1: круг исходного размера (48dp) в правом верхнем углу
// компонента с чёрным фоном размером 240.dp x 120.dp
@Composable
fun CircleTopEnd(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

// Функция 2: синий фон, круг по центру и растянут по размерам компонента
// (получается эллипс). Фиолетовый цвет задаёт параметр colorFilter.
@Composable
fun CircleStretched(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color(0xFF800080))
        )
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun CircleTopEndPreview() {
    FirstComposeProjectTheme { CircleTopEnd() }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun CircleStretchedPreview() {
    FirstComposeProjectTheme { CircleStretched() }
}
