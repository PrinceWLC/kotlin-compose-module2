package com.example.firstcomposeproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme

/**
 * Задание 8. Счётчик выпитой жидкости.
 *
 * Что было исправлено в исходном коде:
 *  1. Состояние объявлено без remember и со стартовым значением 100 — теперь
 *     rememberSaveable { mutableIntStateOf(0) }: значение запоминается между
 *     рекомпозициями и переживает поворот экрана.
 *  2. У второго Text не было параметра text — теперь "$waterCount мл".
 *  3. Spacer(Modifier.height()) вызывался без аргумента — задана высота.
 *  4. ButtonDefaults(...) — такого вызова не существует, нужно
 *     ButtonDefaults.buttonColors(containerColor = ...).
 *  5. Кнопка делала waterCount = 250 (присваивание), а нужно += 250.
 *  6. primaryColor / secondaryColor / buttonTextColor не были объявлены.
 *
 * Добавлено по заданию: счётчик дней подряд (>= 1500 мл) и кнопка «Завершить день».
 */

private val primaryColor = Color(0xFF415F91)
private val secondaryColor = Color(0xFF565F71)
private val buttonTextColor = Color.White

// Порог, при котором день считается «выполненным»
const val DAILY_GOAL_ML = 1500
const val GLASS_ML = 250

@Composable
fun WaterTracker(modifier: Modifier = Modifier) {
    var waterCount by rememberSaveable { mutableIntStateOf(0) }
    // Сколько дней подряд выпито 1500 мл и больше
    var streakDays by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor,
            modifier = Modifier.testTag("waterCount")
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = { waterCount += GLASS_ML }
        ) {
            Text(
                text = "+$GLASS_ML мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = {
                // Норма выполнена — наращиваем серию, иначе серия обнуляется
                streakDays = if (waterCount >= DAILY_GOAL_ML) streakDays + 1 else 0
                // Новый день — счётчик воды обнуляем
                waterCount = 0
            }
        ) {
            Text(
                text = "Завершить день",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Дней подряд с $DAILY_GOAL_ML мл и больше: $streakDays",
            fontSize = 18.sp,
            color = secondaryColor,
            modifier = Modifier.testTag("streakText")
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WaterTrackerPreview() {
    FirstComposeProjectTheme {
        WaterTracker()
    }
}
