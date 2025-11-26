package com.example.myspbstuschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myspbstuschedule.presentation.screens.schedule.ScheduleContent
import com.example.myspbstuschedule.presentation.screens.selection.SelectionContent
import com.example.myspbstuschedule.ui.theme.MySpbstuScheduleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySpbstuScheduleTheme {
//                SelectionContent(
//                    modifier = Modifier
//                        .background(MaterialTheme.colorScheme.background)
//                        .windowInsetsPadding(WindowInsets.safeDrawing)
//                        .padding(8.dp)
//                )

                ScheduleContent(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                )
            }
        }
    }
}

