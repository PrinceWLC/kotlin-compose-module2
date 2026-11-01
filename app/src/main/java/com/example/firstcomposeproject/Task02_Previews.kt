package com.example.firstcomposeproject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme


@Preview(
    name = "portrait",
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp"
)
@Preview(
    name = "landscape",
    showSystemUi = true,
    device = "spec:width=891dp,height=411dp"
)
@Preview(
    name = "round",
    showSystemUi = true,
    showBackground = true,
    backgroundColor = 0xFFFFFF00,
    device = "spec:width=200dp,height=200dp,isRound=true"
)
@Composable
fun HelloPreview() {
    FirstComposeProjectTheme {
        // fillMaxSize: чтобы фон (backgroundColor) занял весь экран, а не только строку текста
        Hello(name = "Android", modifier = Modifier.fillMaxSize())
    }
}
