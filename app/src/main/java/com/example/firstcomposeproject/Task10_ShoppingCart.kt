package com.example.firstcomposeproject

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme
import kotlin.random.Random



data class Cart(
    val products: List<Product>
) {
    // Сумма — производное значение от состава корзины, а не отдельное хранимое поле
    val totalSum: Int
        get() = products.sumOf { it.price }
}

data class Product(
    val id: Int,
    val name: String,
    val price: Int
)

private const val FREE_DELIVERY_THRESHOLD = 500

@Composable
fun ShoppingCartScreen(modifier: Modifier = Modifier) {
    var cart by remember {
        mutableStateOf(
            Cart(
                listOf(
                    Product(0, "Товар #1", 100),
                    Product(1, "Товар #2", 150),
                    Product(2, "Товар #3", 56)
                )
            )
        )
    }
    val context = LocalContext.current

    Column(modifier = modifier.padding(16.dp)) {
        // Только отображение: никаких изменений состояния внутри цикла
        for (product in cart.products) {
            Text(text = product.name + " - " + product.price + " рублей")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Товаров на сумму: ${cart.totalSum} рублей")

        AddProductSection {
            val newCart = cart.copy(
                products = cart.products + Product(
                    id = cart.products.size,
                    name = "Товар #${cart.products.size + 1}",
                    price = Random.nextInt(0, 100)
                )
            )
            cart = newCart
            // Сообщение — реакция на событие, а не часть композиции
            if (newCart.totalSum > FREE_DELIVERY_THRESHOLD) {
                Toast.makeText(context, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
            }
        }

        // Кнопка «Удалить товар» показывается только при наличии товаров
        if (cart.products.isNotEmpty()) {
            RemoveProductSection {
                cart = cart.copy(products = cart.products.dropLast(1))
            }
        }
    }
}

@Composable
fun AddProductSection(onAdd: () -> Unit) {
    Button(onClick = onAdd) {
        Text(text = "Добавить товар")
    }
}

@Composable
fun RemoveProductSection(onRemove: () -> Unit) {
    Button(onClick = onRemove) {
        Text(text = "Удалить товар")
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingCartPreview() {
    FirstComposeProjectTheme {
        ShoppingCartScreen()
    }
}
