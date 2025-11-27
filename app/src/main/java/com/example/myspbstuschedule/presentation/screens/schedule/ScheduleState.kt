package com.example.myspbstuschedule.presentation.screens.schedule

import com.example.myspbstuschedule.domain.model.Lesson

sealed class ScheduleState {

    data object Empty : ScheduleState()

    data object Loading : ScheduleState()

    data class Error(
        val message: String
    ) : ScheduleState()

    data class Content(
        val lessons: List<Lesson>
    ) : ScheduleState()
}