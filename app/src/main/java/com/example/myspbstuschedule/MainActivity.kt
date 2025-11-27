package com.example.myspbstuschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myspbstuschedule.presentation.screens.MainScreen
import com.example.myspbstuschedule.ui.theme.MySpbstuScheduleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySpbstuScheduleTheme {
                MainScreen()
            }
        }
    }
}

