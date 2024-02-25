package com.example.myfirstjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import com.example.myfirstjetpackcompose.pojo.Cloudiness
import com.example.myfirstjetpackcompose.pojo.Kind
import com.example.myfirstjetpackcompose.pojo.Precipitation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column {

            }
        }
    }
}
