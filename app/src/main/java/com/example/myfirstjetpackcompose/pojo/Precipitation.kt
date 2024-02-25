package com.example.myfirstjetpackcompose.pojo

/**
 * Осадки
 * */
data class Precipitation (
    /** Тип осадков */
    val type : Int = 0,

    /** Количество осадков в мм. */
    val amount : Float? = null,

    /** Интенсивность осадков */
    val intensity : Int = 0
)