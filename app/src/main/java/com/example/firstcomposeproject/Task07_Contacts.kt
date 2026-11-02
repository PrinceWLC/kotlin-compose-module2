package com.example.firstcomposeproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme


data class Contact(
    val name: String,
    val patronymic: String,
    val surname: String,
    val phone: String?,
    val address: String
)

val sampleContacts = listOf(
    Contact(
        name = "Евгений",
        patronymic = "Андреевич",
        surname = "Лукашин",
        phone = "+7 495 495 95 95",
        address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12"
    ),
    Contact(
        name = "Василий",
        patronymic = "Егорович",
        surname = "Кузякин",
        phone = null, // телефона нет — выводим прочерк
        address = "Ивановская область, дер. Крутово, д. 4"
    ),
    Contact(
        name = "Людмила",
        patronymic = "Прокофьевна",
        surname = "Калугина",
        phone = "+7 495 788 78 78",
        address = "Москва, Большая Никитская, д. 43, кв. 290"
    )
)

// Пять строк контакта (без выравнивания)
@Composable
fun ContactLines(contact: Contact, centered: Boolean, modifier: Modifier = Modifier) {
    val align = if (centered) TextAlign.Center else TextAlign.Start
    Column(
        modifier = modifier,
        horizontalAlignment = if (centered) Alignment.CenterHorizontally else Alignment.Start
    ) {
        Text("Имя: ${contact.name}", textAlign = align)
        Text("Отчество: ${contact.patronymic}", textAlign = align)
        Text("Фамилия: ${contact.surname}", textAlign = align)
        Text("Мобильный телефон: ${contact.phone ?: "—"}", textAlign = align)
        Text("Адрес: ${contact.address}", textAlign = align)
    }
}

// Картинка 1: простой Column с пятью строками, без фона
@Composable
fun ContactColumn(contact: Contact, modifier: Modifier = Modifier) {
    ContactLines(contact = contact, centered = false, modifier = modifier)
}

// Картинка 2: список из трёх контактов, серые карточки, текст по центру
@Composable
fun ContactList(contacts: List<Contact>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        contacts.forEach { contact ->
            ContactLines(
                contact = contact,
                centered = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(8.dp)
            )
        }
    }
}

// Картинка 3: Row — текст контакта слева (Column), картинка-звезда справа
@Composable
fun ContactRow(contact: Contact, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactLines(
            contact = contact,
            centered = true,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(R.drawable.ic_star),
            contentDescription = "Избранный контакт"
        )
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactColumnPreview() {
    FirstComposeProjectTheme { ContactColumn(sampleContacts[0]) }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ListPreview() {
    FirstComposeProjectTheme { ContactList(sampleContacts) }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactRowPreview() {
    FirstComposeProjectTheme {
        Column(Modifier.padding(8.dp)) { ContactRow(sampleContacts[0]) }
    }
}
