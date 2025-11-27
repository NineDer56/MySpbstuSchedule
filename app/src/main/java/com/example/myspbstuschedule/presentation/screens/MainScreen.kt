package com.example.myspbstuschedule.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.myspbstuschedule.presentation.screens.selection.SelectionScreen

@Composable
fun MainScreen() {
    SelectionScreen(
        PaddingValues(
            start = 8.dp,
            end = 8.dp,
            top = 8.dp
        )
    )
}