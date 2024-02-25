package com.example.myfirstjetpackcompose.pojo

/** Тип погодных данных */
enum class Kind(val value : String) {
    /** Наблюдение */
    Obs(value = "Наблюдение"),

    /** Прогноз (представляется, если нет наблюдения) */
    Frc(value = "Прогноз (представляется, если нет наблюдения)")

}