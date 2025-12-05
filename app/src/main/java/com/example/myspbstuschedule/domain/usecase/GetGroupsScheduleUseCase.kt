package com.example.myspbstuschedule.domain.usecase

import com.example.myspbstuschedule.domain.model.Schedule
import com.example.myspbstuschedule.domain.repository.ScheduleRepository
import javax.inject.Inject

class GetGroupsScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(id: Int, date: String): Schedule {
        return repository.getGroupsSchedule(id, date)
    }
}
