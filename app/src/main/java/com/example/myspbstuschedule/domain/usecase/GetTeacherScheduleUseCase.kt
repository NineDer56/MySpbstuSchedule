package com.example.myspbstuschedule.domain.usecase

import com.example.myspbstuschedule.domain.model.Schedule
import com.example.myspbstuschedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeacherScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    operator fun invoke(teacherId : Int, date : String) : Flow<Schedule>{
        return repository.getTeacherSchedule(teacherId, date)
    }
}