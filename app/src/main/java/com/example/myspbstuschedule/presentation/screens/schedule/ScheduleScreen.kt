package com.example.myspbstuschedule.presentation.screens.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ScheduleScreen(
    contentPadding: PaddingValues
) {

    val viewModel: ScheduleViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()


    when (val currentState = state) {
        is ScheduleState.Initial -> {

        }

        is ScheduleState.Loading -> {

        }

        is ScheduleState.Content -> {
            ScheduleContent(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(contentPadding),
                dates = currentState.dates,
                lessons = currentState.lessons
            )
        }

        is ScheduleState.Error -> {

        }
    }
}