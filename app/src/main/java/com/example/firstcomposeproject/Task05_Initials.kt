package com.example.firstcomposeproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme


@Composable
fun Initials(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxHeight()
            .aspectRatio(1f), // квадрат по высоте, чтобы получился круг, а не эллипс
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Text(text = text, color = Color.Black)
    }
}

@Composable
fun InitialsContainer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Initials(text = "АБ")
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun Task05Preview() {
    FirstComposeProjectTheme {
        InitialsContainer()
    }
}
