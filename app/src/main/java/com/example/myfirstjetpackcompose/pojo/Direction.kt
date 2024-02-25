package com.example.myfirstjetpackcompose.pojo

/**
 * Направление
 * */
data class Direction (
    /** По шкале от 1 до 8 */
    val scale_8 : Int = 0,

    /** В градусах */
    val degree : Int = 0
)