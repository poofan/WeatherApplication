package com.example.myfirstjetpackcompose

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfirstjetpackcompose.pojo.Kind
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KindTest {

    @Test
    fun getEntries() {
    }

    @Test
    fun values() {
    }

    @Test
    fun valueOf() {
        Kind.valueOf(1.toString())
    }
}