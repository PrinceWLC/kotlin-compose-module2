package com.example.firstcomposeproject

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme


@Composable
fun Hello(name: String?, modifier: Modifier = Modifier) {
    Text(
        text = if (name != null) "Привет, $name!" else "Имя не задано",
        modifier = modifier
    )
}

// Проверка варианта с конкретным именем
@Preview(name = "Hello: имя задано", showBackground = true)
@Composable
fun HelloNamePreview() {
    FirstComposeProjectTheme {
        Hello(name = "Android")
    }
}

// Проверка варианта с null
@Preview(name = "Hello: null", showBackground = true)
@Composable
fun HelloNullPreview() {
    FirstComposeProjectTheme {
        Hello(name = null)
    }
}
