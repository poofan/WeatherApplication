package com.example.myfirstjetpackcompose.activity.function

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstjetpackcompose.R
import com.example.myfirstjetpackcompose.ui.theme.helveticaFamily

/**
 * Основная функция отрисовки главного окна приложения
 * */
@Composable
@Preview(showBackground = true)
fun CreateMainActivity() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            // Название приложения
            ApplicationNameBox()

            // Поле ввода локации и кнопка Обновить
            CreateTextEditAndButtonRefresh(onSubmit = {})
        }
    }
}

/**
 * Функция отрисовки названия прогноза погоды
 * */
@Composable
private fun ApplicationNameBox() {
    val appName = "Weather App"

    // основной контейнер, в котором хранится название приложения
    Box(
        modifier = Modifier
            .padding(top = 35.dp, start = 35.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        // Элемент "Название приложения"
        Text(
            style = TextStyle(
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = helveticaFamily,
            ),
            text = appName
        )
    }
}

/**
 * Функция отрисовки поля ввода города и кнопка обновить
 * */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CreateTextEditAndButtonRefresh(onSubmit : (String) -> Unit) {
    // Текст, который ввёл пользователь
    val text = remember { mutableStateOf("") }

    // Создаем состояние, которое будет отслеживать, был ли курсор видимым
    val isCursorVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(top = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 1.dp,
                    brush = Brush.linearGradient(listOf(Color.Gray, Color.Gray))
                ),
                modifier = Modifier
                    .padding(start = 35.dp)
                    .fillMaxWidth(0.6f),
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                TextField(
                    value = text.value,
                    onValueChange = { newText ->
                        text.value = newText
                    },
                    label = { Text("Ваш город...", textAlign = TextAlign.Center) },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        focusedContainerColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    singleLine = true
                )
            }
            TextButton(
                onClick = { },
                modifier = Modifier
                    .padding(end = 65.dp)
                    .size(55.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 1.dp,
                    brush = Brush.linearGradient(listOf(Color.Gray, Color.Gray))
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Image(
                    painter = painterResource(id = R.drawable.refresh_button),
                    contentDescription = "refresh_button"
                )
            }
        }
    }
}