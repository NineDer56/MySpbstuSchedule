package com.example.myspbstuschedule.presentation.screens.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode

@Composable
fun ScheduleScreen(
    contentPadding: PaddingValues,
    id : Int,
    mode : SearchMode
) {

    val viewModel: ScheduleViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize()
    ) {
        ScheduleContent(
            modifier = Modifier
                .padding(contentPadding),
            dates = viewModel.get7DaysOfWeek(),
            onDateChange = { offset ->
                viewModel.get7DaysOfWeek(offset)
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(contentPadding)
        ) {
            when (val currentState = state) {
                is ScheduleState.Empty -> {
                    ScheduleEmpty(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                is ScheduleState.Loading -> {
                    ScheduleLoading(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                is ScheduleState.Content -> {
                    ScheduleContentItems(
                        modifier = Modifier
                            .fillMaxSize(),
                        lessons = currentState.lessons
                    )
                }

                is ScheduleState.Error -> {
                    ScheduleError(
                        modifier = Modifier
                            .fillMaxSize(),
                        message = currentState.message
                    )
                }
            }

        }

    }

}