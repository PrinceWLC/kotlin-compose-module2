package com.example.firstcomposeproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme


// Вариант 1: зелёный цвет, 16.sp, курсив, выравнивание по центру
@Composable
fun GreenItalicText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.jetpack_compose_description),
        color = Color.Green,
        fontSize = 16.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        // для выравнивания по центру тексту нужна вся ширина родителя
        modifier = modifier.fillMaxWidth()
    )
}

// Вариант 2: одна строка, в конце многоточие, если текст не умещается
@Composable
fun SingleLineText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.jetpack_compose_description),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

// Вариант 3: отступ первой строки 48.sp, шрифт 24.sp, чёрный цвет,
// зелёный фон, текст подчёркнут
@Composable
fun IndentedText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.jetpack_compose_description),
        color = Color.Black,
        fontSize = 24.sp,
        textDecoration = TextDecoration.Underline,
        style = TextStyle(textIndent = TextIndent(firstLine = 48.sp)),
        modifier = modifier.background(Color.Green)
    )
}

@Preview(name = "Зелёный курсив по центру", showBackground = true)
@Composable
fun GreenItalicTextPreview() {
    FirstComposeProjectTheme { GreenItalicText() }
}

@Preview(name = "Одна строка с многоточием", showBackground = true)
@Composable
fun SingleLineTextPreview() {
    FirstComposeProjectTheme { SingleLineText() }
}

@Preview(name = "Отступ, подчёркивание, зелёный фон", showBackground = true)
@Composable
fun IndentedTextPreview() {
    FirstComposeProjectTheme { IndentedText() }
}
