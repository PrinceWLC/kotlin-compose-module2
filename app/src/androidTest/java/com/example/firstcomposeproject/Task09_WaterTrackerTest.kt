package com.example.firstcomposeproject

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class WaterTrackerUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            FirstComposeProjectTheme {
                WaterTracker()
            }
        }
    }

    // Нажимает кнопку «+250 мл» указанное число раз
    private fun addGlasses(times: Int) {
        repeat(times) {
            composeTestRule.onNodeWithText("+250 мл").performClick()
        }
    }

    // 1) Кнопка добавления жидкости: есть на экране и увеличивает счётчик на 250 мл
    @Test
    fun addButton_existsAndAdds250() {
        composeTestRule.onNodeWithText("+250 мл").assertIsDisplayed()
        composeTestRule.onNodeWithTag("waterCount").assertTextEquals("0 мл")

        addGlasses(1)
        composeTestRule.onNodeWithTag("waterCount").assertTextEquals("250 мл")

        addGlasses(2)
        composeTestRule.onNodeWithTag("waterCount").assertTextEquals("750 мл")
    }

    // 2) Кнопка «Завершить день»: есть на экране и сбрасывает счётчик воды в ноль
    @Test
    fun finishDayButton_existsAndResetsWater() {
        composeTestRule.onNodeWithText("Завершить день").assertIsDisplayed()

        addGlasses(2)
        composeTestRule.onNodeWithTag("waterCount").assertTextEquals("500 мл")

        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithTag("waterCount").assertTextEquals("0 мл")
    }

    // 3) Текст со счётчиком дней подряд: изначально 0
    @Test
    fun streakText_isDisplayedWithZeroInitially() {
        composeTestRule.onNodeWithTag("streakText").assertIsDisplayed()
        composeTestRule.onNodeWithTag("streakText")
            .assertTextEquals("Дней подряд с 1500 мл и больше: 0")
    }

    // 3) Выпито 1500 мл и больше -> серия растёт
    @Test
    fun streakText_increasesWhenGoalReached() {
        addGlasses(6) // 6 * 250 = 1500 мл
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithTag("streakText")
            .assertTextEquals("Дней подряд с 1500 мл и больше: 1")

        addGlasses(7) // 1750 мл — норма снова выполнена
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithTag("streakText")
            .assertTextEquals("Дней подряд с 1500 мл и больше: 2")
    }

    // 3) Выпито меньше 1500 мл -> серия обнуляется
    @Test
    fun streakText_resetsWhenGoalMissed() {
        addGlasses(6)
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithTag("streakText")
            .assertTextEquals("Дней подряд с 1500 мл и больше: 1")

        addGlasses(5) // 1250 мл — меньше нормы
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithTag("streakText")
            .assertTextEquals("Дней подряд с 1500 мл и больше: 0")
    }
}
