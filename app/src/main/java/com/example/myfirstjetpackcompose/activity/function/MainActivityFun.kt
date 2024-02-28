package com.example.myfirstjetpackcompose.activity.function

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.draw.clip
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
import com.example.myfirstjetpackcompose.ui.theme.GrayLight50
import com.example.myfirstjetpackcompose.ui.theme.SeaBlue
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
        LazyColumn(
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(count = 1){
                // Название приложения
                ApplicationNameBox()

                // Поле ввода локации и кнопка Обновить
                CreateTextEditAndButtonRefresh(onSubmit = {})

                // Основной контейнер отображения текущей температуры
                CreateMainTextTemperatureInfo()

                // отображение переключателя
                CreateTabRowSwitch()
            }
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


/**
 * Функция отрисовки текста:
 * 1. Название города
 * 2. Градусы цельсия (текущие)
 * 3. Состояние погоды (облачно, ясно и т.д.)
 * 4. Картинка (иконка) состояния погоды
 * 5. Градусы (ощущается как ...)
 * */
@Composable
private fun CreateMainTextTemperatureInfo(){
    // основной бокс, в котором будет лежать вся необходимая информация
    Box(
        modifier = Modifier
            .padding(start = 35.dp, top = 25.dp, end = 35.dp)
            .fillMaxWidth()
    ) {
        Column {
            // Название города
            Text(
                text = "Санкт-Петербург",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            )
            // инфо по текущей температуре
            Row(
                modifier = Modifier.padding(top = 10.dp)
            ) {
                // градусы цельсия
                Text(
                    text = "23°C,",
                    style = TextStyle(
                        fontSize = 24.sp
                    )
                )
                // Описание состояния погоды
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Ясно",
                    style = TextStyle(
                        fontSize = 24.sp
                    )
                )
            }
            // инфо по "ощущается как ..."
            Row(
                modifier = Modifier
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // иконка состояния погоды
                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.cloudy_icon),
                    contentDescription = "icon_feels_like_image",
                    )
                // температура "ощущается как..."
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Ощущается как",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "25°C",
                    style = TextStyle(
                        fontSize = 24.sp
                    )
                )
            }
        }
    }
}

/**
 * Функция отрисовки переключателя между прогнозом на неделю и по часам текущего дня
 * */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CreateTabRowSwitch(){
    val tabList = listOf("По часам", "За неделю")
    val state = rememberPagerState{ tabList.size }

    HorizontalPager(
        state = state,
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(start = 35.dp, end = 35.dp, top = 35.dp)
    ) { page ->
        TabRow(
                selectedTabIndex = page,
                indicator = {pos ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(currentTabPosition = pos[0])
                    )
                },
                containerColor = GrayLight50) {
                tabList.forEachIndexed { index, s ->
                    Tab(
                        selectedContentColor = SeaBlue,
                        unselectedContentColor = Color.Black,
                        selected = false,
                        onClick = { /*TODO*/ },
                        text = {
                            Text(text = s)
                        })
                }
            }
    }
}