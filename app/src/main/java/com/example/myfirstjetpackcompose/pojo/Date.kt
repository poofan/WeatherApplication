package com.example.myfirstjetpackcompose.pojo

/**
 * Дата и время данных
 * */
data class Date (
    /** По стандарту UTC */
    val UTC : String? = null,

    /** В формате UNIX по стандарту UTC */
    val unix : Int = 0,

    /** По локальному времени географического объекта */
    val local : String? = null,

    /** Разница в минутах между локальным временем географического объекта и временем по UTC */
    val time_zone_offset : Int = 0
)