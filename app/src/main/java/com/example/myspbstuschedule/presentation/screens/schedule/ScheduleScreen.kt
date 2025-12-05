package com.example.myspbstuschedule.presentation.screens.schedule

import android.util.Log
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

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize()
    ) {
        ScheduleContent(
            dates = state.weekDays,
            selectedDay = state.selectedDayOfWeek,
            onDateChange = { offset ->
                viewModel.get7DaysOfWeek(offset)
                viewModel.getSchedule()
                Log.d("Test", "onDateChange $offset")
            },
            onDayOfWeekClick = { dayOfWeek ->
                viewModel.selectDay(dayOfWeek)
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(contentPadding)
        ) {
            when {

                (state.isLoading) -> {
                    ScheduleLoading(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                (state.error != null && state.schedule == null) -> {
                    ScheduleError(
                        modifier = Modifier
                            .fillMaxSize(),
                        message = state.error ?: "Неизвестная ошибка"
                    )
                }

                (state.schedule != null && state.currentLessons.isNotEmpty()) -> {
                    ScheduleContentItems(
                        modifier = Modifier
                            .padding(contentPadding)
                            .fillMaxSize(),
                        lessons = state.currentLessons
                    )
                }

                else -> {
                    ScheduleEmpty(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }

        }

    }

}