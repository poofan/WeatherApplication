package com.example.myfirstjetpackcompose.pojo

/** Ветер */
data class Wind (
    /** Направление */
    val direction : Direction? = null,

    /** Скорость */
    val speed : Speed? = null
)