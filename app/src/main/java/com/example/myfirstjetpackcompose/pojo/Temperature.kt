package com.example.myfirstjetpackcompose.pojo

/**
 * Температура
 * */
data class Temperature (
    /** Воздух */
    val air : Air? = null,

    /** Комфорт (по ощущению) */
    val comfort :Comfort? = null,

    /** Вода */
    val water : Water? = null
)