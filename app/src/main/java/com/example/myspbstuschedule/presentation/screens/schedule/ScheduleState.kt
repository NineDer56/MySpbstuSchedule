package com.example.myspbstuschedule.presentation.screens.schedule

import com.example.myspbstuschedule.domain.model.Lesson
import java.time.LocalDate

sealed class ScheduleState {

    data object Initial : ScheduleState()

    data object Loading : ScheduleState()

    data class Error(
        val message: String
    ) : ScheduleState()

    data class Content(
        val dates: List<LocalDate>,
        val lessons: List<Lesson>
    ) : ScheduleState()
}