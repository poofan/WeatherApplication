package com.example.myfirstjetpackcompose.activity.function

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.myfirstjetpackcompose.pojo.WeatherPojo
import com.example.myfirstjetpackcompose.ui.theme.BlueGreenMedium
import com.example.myfirstjetpackcompose.ui.theme.BlueGreenHeavy
import com.example.myfirstjetpackcompose.ui.theme.BlueGreenLight
import com.example.myfirstjetpackcompose.ui.theme.PuertoRico
import com.example.myfirstjetpackcompose.ui.theme.SeaBlue
import com.example.myfirstjetpackcompose.ui.theme.WhiteLight
import com.example.myfirstjetpackcompose.ui.theme.WhiteWithBlue
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
fun MainWeatherTemplateBox() {
    val applicationName = "Weather"

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
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = helveticaFamily,
                    letterSpacing = 25.sp,
                    color = WhiteWithBlue
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(15.dp)
            )
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
fun CardWeatherForCurrentDay(weatherPojo: WeatherPojo) {
    Card(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxHeight(0.7f),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(BlueGreenHeavy, BlueGreenMedium, BlueGreenLight),
                    )
                )
                .fillMaxSize()
        ) {
            // Текст Название города
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = "Санкт-Петербург",
                style = TextStyle(
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = WhiteLight
                ),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // День недели, дата, скорость ветра, влажность
                Column {
                    // День недели
                    Text(
                        modifier = Modifier
                            .padding(start = 15.dp, top = 30.dp),
                        text = "Понедельник",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = WhiteLight
                        )
                    )
                    // Дата
                    Text(
                        modifier = Modifier
                            .padding(start = 15.dp, top = 10.dp),
                        text = "26.02.2024",
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = WhiteLight
                        )
                    )
                    // Скорость ветра
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.wind_icon),
                            contentDescription = "drop_icon",
                            modifier = Modifier
                                .padding(start = 10.dp, top = 15.dp)
                                .size(35.dp)
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp, top = 13.dp),
                            text = "6 км/ч",
                            style = TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = WhiteLight
                            )
                        )
                    }

                    // Влажность
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.drop_icon),
                            contentDescription = "drop_icon",
                            modifier = Modifier
                                .padding(start = 10.dp, top = 15.dp)
                                .size(35.dp)
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 10.dp),
                            text = "30%",
                            style = TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = WhiteLight
                            )
                        )
                    }
                }
                // Строки с температурой
                Row(
                    modifier = Modifier.padding(end = 15.dp)
                ) {
                    // стрелка вверх
                    Image(
                        modifier = Modifier
                            .padding(top = 35.dp),
                        painter = painterResource(id = R.drawable.arrow_up_icon),
                        contentDescription = "High temperature"
                    )
                    // градусы (максимальная температура за сегодня)
                    Text(
                        text = "18°",
                        modifier = Modifier
                            .padding(top = 30.dp),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = WhiteLight
                        )
                    )
                    // стрелка вниз
                    Image(
                        modifier = Modifier
                            .padding(top = 35.dp),
                        painter = painterResource(id = R.drawable.arrow_down_icon),
                        contentDescription = "Low temperature"
                    )
                    // градусы (минимальная температура за сегодня)
                    Text(
                        text = "18°",
                        modifier = Modifier
                            .padding(top = 30.dp),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = WhiteLight
                        )
                    )
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
                ) {
                Image(
                    painter = painterResource(id = R.drawable.cloudy_icon),
                    contentDescription = "cloudy_img",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(start = 10.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(end = 10.dp),
                    text = "20°",
                    style = TextStyle(
                        fontSize = 75.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }
    }
    Card(
        modifier = Modifier
            .padding(10.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(BlueGreenMedium, BlueGreenLight)
                    )
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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

@Composable
fun CardWeatherForWeek() {

}

