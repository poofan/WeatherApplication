package com.example.myfirstjetpackcompose.activity.function

import android.content.Context
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.myfirstjetpackcompose.R
import com.example.myfirstjetpackcompose.ui.theme.GrayLight50
import com.example.myfirstjetpackcompose.ui.theme.helveticaFamily
import kotlinx.coroutines.launch

/**
 * Основная функция отрисовки главного окна приложения
 * */
@Composable
fun CreateMainActivity(context: Context) {
    val cityName = rememberSaveable{
        mutableStateOf(loadCityName(context))
    }

    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation
    val sidePadding = if (orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
        // Больший отступ при горизонтальной ориентации
        45.dp
    } else {
        // Меньший отступ при вертикальной ориентации
        35.dp
    }

    Box(
        modifier = Modifier
            .padding(start = sidePadding, end = sidePadding)
            .fillMaxWidth()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(count = 1) {
                // Название приложения
                ApplicationNameBox()

                // Поле ввода локации и кнопка Обновить
                CreateTextEditAndButtonRefresh(onTextSubmitted = {
                    cityName.value = it
                })

                // Основной контейнер отображения текущей температуры
                CreateMainTextTemperatureInfo(cityName.value)

                // отображение переключателя
                CreateTabRowSwitch()
            }
        }
    }

    // Сохраним город, если были измнения
    DisposableEffect(Unit) {
        onDispose {
            saveCityName(cityName.value, context)
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
            .padding(top = 35.dp)
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
private fun CreateTextEditAndButtonRefresh(onTextSubmitted: (String) -> Unit) {
    // Текст, который ввёл пользователь
    val currentText = remember { mutableStateOf("") }
    val initialText = remember { mutableStateOf("") }

    // Клавиатура пользователя
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    // запоминаем фокус на элементе
    val focusManager = LocalFocusManager.current

    // список городов
    val cities = listOf(
        "Москва",
        "Санкт-Петербург",
        "Новосибирск",
        "Екатеринбург",
        "Казань",
        "Нижний Новгород",
        "Челябинск",
        "Самара",
        "Омск",
        "Ростов-на-Дону"
    )

    // отфильтрованный список (после введённого пользователем символа)
    val filteredCities = remember { mutableListOf<String>() }

    // скрыть/открыть выпадающий список
    val isDropdownExpanded = remember { mutableStateOf(false) }

    // функция для фильтрации городов
    fun filterCities(input: String) {
        filteredCities.clear()
        filteredCities.addAll(cities.filter { it.contains(input, ignoreCase = true) })
        isDropdownExpanded.value = true
    }

    /**
     * функция делает следующее:
     * 1. Передаёт введённый пользователем текст в функцию onTextSubmitted
     * 2. Скрывает клавиатуру
     * 3. Убирает фокус с поля TextField
     * 4. Очиает поле TextField
     * */
    fun submitText() {
        onTextSubmitted(currentText.value) // передаём в метод введённый текст
        softwareKeyboardController?.hide() // скрываем клавиатуру
        focusManager.clearFocus() // убираем фокус с поля
        currentText.value = "" // Очищаем текст после отправки
        filteredCities.clear() // очищаем список городов
        isDropdownExpanded.value = false // скрываем выпадающий список
    }

    // основной контейнер
    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxSize()
    ) {
        // контейнер, разделяющий поле ввода и кнопку обновить по строчно
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // поле ввода названия города
            TextField(
                value = currentText.value,
                onValueChange = { newText ->
                    currentText.value = newText
                    filterCities(newText)
                },
                label = { Text("Ваш город...") },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    unfocusedContainerColor = Color.Transparent
                ),
                singleLine = true,
                modifier = Modifier
                    .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.small)
            )
            // выпадающий список
            DropdownMenu(
                expanded = isDropdownExpanded.value,
                onDismissRequest = {
                    isDropdownExpanded.value = false

                    // скрываем клавиатуру
                    softwareKeyboardController?.hide()
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                content = {
                    filteredCities.forEach { city ->
                        Log.d("MyTag", city)
                        DropdownMenuItem(
                            text = {
                                Text(text = city)
                            },
                            onClick = {
                                currentText.value = city
                                initialText.value = city
                                submitText()
                            })
                    }
                },
                properties = PopupProperties(focusable = false)
            )
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
private fun CreateMainTextTemperatureInfo(cityName: String) {
    // основной бокс, в котором будет лежать вся необходимая информация
    Box(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth()
    ) {
        Column {
            // Название города
            Text(
                text = cityName,
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
private fun CreateTabRowSwitch() {
    val tabList = listOf("По часам", "За неделю")
    val pagerState = rememberPagerState { tabList.size }
    val scrollScope = rememberCoroutineScope()

    // Создаем состояние для отслеживания выбранной вкладки
    val (selectedTabIndex, setSelectedTabIndex) = remember { mutableIntStateOf(0) }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        indicator = { pos ->
            pos.forEachIndexed { index, tabPosition ->
                if (index == selectedTabIndex) {
                    TabRowDefaults.Indicator(
                        Modifier
                            .fillMaxWidth()
                            .tabIndicatorOffset(currentTabPosition = tabPosition)
                            .size(0.dp)
                            .background(Color.Transparent)
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp)
            .background(Color.Transparent)
    ) {
        tabList.forEachIndexed { index, s ->
            var customModifire: Modifier = Modifier.clickable {
                setSelectedTabIndex(index)
            }

            if (selectedTabIndex == index) {
                customModifire = Modifier
                    .background(
                        color = GrayLight50,
                        shape = RoundedCornerShape(5.dp)
                    )
            }
            Tab(
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black,
                selected = selectedTabIndex == index,
                onClick = {
                    setSelectedTabIndex(index)

                    // Обновляем состояние pagerState при переключении вкладок
                    scrollScope.launch {
                        pagerState.scrollToPage(index)
                    }
                },
                text = {
                    Text(text = s)
                },
                modifier = customModifire
            )
        }
    }
    Crossfade(
        targetState = selectedTabIndex,
        animationSpec = TweenSpec(
            durationMillis = 500, // Длительность анимации в миллисекундах
            easing = FastOutSlowInEasing
        ), label = ""
    ) {
        WeatherListInfo(it)
    }
}

/**
 * Фукнция отрисовки прогоза погоды по :
 * 1. Часам текущего дня
 * 2. На неделю вперёд
 * */
@Composable
private fun WeatherListInfo(selectedTabIndex: Int) {
    Box(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            contentPadding = PaddingValues(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .requiredHeight(300.dp)
                .fillMaxHeight()
        ) {
            items(10) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    when (selectedTabIndex) {
                        // если выбрана вкладка "По часам"
                        0 -> {
                            // время
                            Text(
                                text = "1$it:00",
                                style = TextStyle(
                                    fontSize = 20.sp
                                )
                            )
                            // иконка состояния погоды
                            Image(
                                painter = painterResource(id = R.drawable.cloudy_icon),
                                contentDescription = "weather_list_icons",
                                modifier = Modifier
                                    .size(40.dp)
                            )
                            // текущая температура
                            Text(
                                text = "18°C",
                                style = TextStyle(
                                    fontSize = 20.sp
                                )
                            )
                        }

                        // если выбрана вкладка "За неделю"
                        1 -> {
                            // название дня недели
                            Text(
                                text = "Day $it",
                                style = TextStyle(
                                    fontSize = 20.sp
                                )
                            )
                            // температура наимаеньшая-наибольшая
                            Text(
                                text = "$it°C / 18°C",
                                style = TextStyle(
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Сохранение в кэш приложения данных по городу
 * */
private const val PREFS_NAME = "MyPrefs"
private const val CITY_NAME_KEY = "cityName"

private fun saveCityName(cityName: String, context: Context) {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    prefs.edit().putString(CITY_NAME_KEY, cityName).apply()
}

private fun loadCityName(context: Context): String {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    return prefs.getString(CITY_NAME_KEY, "Москва") ?: "Москва"
}