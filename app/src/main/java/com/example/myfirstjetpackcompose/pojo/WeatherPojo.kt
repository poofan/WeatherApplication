package com.example.myfirstjetpackcompose.pojo

/**
 * Класс, содержащий структуру ответа на запрос прогноза погоды у стороннего сервиса
 * */
data class WeatherPojo (
    /** Тип погодных данных */
    val kind : Kind? = null,

    /** Дата и время данных */
    val date : Date? = null,

    /** Температура */
    val temperature : Temperature? = null,

    /** Описание погоды */
    val description : Description? = null,

    /** Влажность */
    val humidity : Humidity? = null,

    /** Атмосферное давление */
    val pressure : Pressure? = null,

    /** Облачность */
    val cloudiness : Cloudiness? = null,

    /** Гроза */
    val storm : Storm? = null,

    /** Осадки */
    val precipitation : Precipitation? = null,

    /** Код погодного явления */
    val phenomenon : Int = 0,

    /** Иконка погоды */
    val icon : String? = null,

    /** Геомагнитное поле */
    val gm : Int = 0,

    /** Ветер */
    val wind : Wind? = null
)