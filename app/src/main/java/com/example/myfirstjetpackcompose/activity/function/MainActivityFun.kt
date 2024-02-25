package com.example.myfirstjetpackcompose.activity.function

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstjetpackcompose.pojo.WeatherPojo
import com.example.myfirstjetpackcompose.ui.theme.BlueGreenMedium
import com.example.myfirstjetpackcompose.ui.theme.BlueGreenHeavy
import com.example.myfirstjetpackcompose.ui.theme.BlueGreenLight
import com.example.myfirstjetpackcompose.ui.theme.PuertoRico
import com.example.myfirstjetpackcompose.ui.theme.SeaBlue
import com.example.myfirstjetpackcompose.ui.theme.WhiteLite
import com.example.myfirstjetpackcompose.ui.theme.helveticaFamily

/**
 * Главная функция отрисовки секции с Box-контейнером, который содержит информацию (другие контейнеры):</br>
 * 1. Текущая дата со временем и днём недели</br>
 * 2. Картинка с текущей погодой (солнечно, облачно и т.д.)</br>
 * 3. Температуру в градусах цельсия (максимальную и минимальную)</br>
 * 4. Текущую температуру в градусах цельсия</br>
 * 5. Название города</br>
 * 6. Процент влажности</br>
 * 7. Скорость ветра</br>
 * 8. Прогноз на неделю, где отображается:
 * - Дни недели
 * - Средняя температура за день
 * - Иконка погоды (солнечно, облачно и т.д.)
 * */
@Preview(showBackground = true)
@Composable
fun MainWeatherTemplateBox()
{
    val applicationName = "-Weather-"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(SeaBlue, PuertoRico)
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Добавим текст (название приложения)
            Text(
                fontSize = 24.sp,
                text = applicationName.uppercase(),
                style = TextStyle(
                    fontFamily = helveticaFamily,
                    letterSpacing = 25.sp,
                    color = WhiteLite),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(15.dp))
            CardWeatherForCurrentDay(WeatherPojo())
        }
    }
}

/**
 * Функция отрисовки секции-карточки, содержащей:
 * 1. Текущая дата со временем и днём недели</br>
 * 2. Картинка с текущей погодой (солнечно, облачно и т.д.)</br>
 * 3. Температуру в градусах цельсия (максимальную и минимальную)</br>
 * 4. Текущую температуру в градусах цельсия</br>
 * 5. Название города</br>
 * 6. Процент влажности</br>
 * 7. Скорость ветра
 * */
@Composable
fun CardWeatherForCurrentDay(weatherPojo : WeatherPojo)
{
    Card(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)) {
        Row (
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(BlueGreenHeavy, BlueGreenMedium, BlueGreenLight),
                    )
                ),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "fsdfs")
            Text(text = "asdfasdfsd")
        }
    }
    Card(modifier = Modifier
        .padding(10.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(BlueGreenMedium, BlueGreenLight)
                    )
                ),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "asdfasdfsd")
            Text(text = "asdfasdfsd")
        }
    }
}

/**
 * Функция отрисовки карточки прогноза на неделю вперёд. Содержит:</br>
 * 1. Название дня недели
 * 2. Картинку погоды (солнечно, облачно и т.д.)
 * 3. Температуру среднюю за день
 * */
@Preview(showBackground = true)
@Composable
fun CardWeatherForWeek()
{

}

