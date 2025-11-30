package com.example.myspbstuschedule.presentation.screens.schedule

import com.example.myspbstuschedule.domain.model.Lesson
import com.example.myspbstuschedule.domain.model.Schedule
import java.time.LocalDate

data class ScheduleState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val selectedDayOfWeek : Int = LocalDate.now().dayOfWeek.value,
    val weekDays : List<LocalDate> = emptyList(),
    val schedule: Schedule? = null
) {
    val currentLessons : List<Lesson>
        get() = schedule
            ?.days
            ?.firstOrNull { it.weekday == selectedDayOfWeek }
            ?.lessons
            .orEmpty()

}