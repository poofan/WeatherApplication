package com.example.myfirstjetpackcompose.pojo

/**
 * Облачность
 * */
data class Cloudiness (
    /** В процентах */
    val percent : Int = 0,

    /** По шкале от 0 до 3 */
    val type : Int = 0
)