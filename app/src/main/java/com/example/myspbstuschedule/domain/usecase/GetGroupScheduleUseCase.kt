package com.example.myspbstuschedule.domain.usecase

import com.example.myspbstuschedule.domain.model.Schedule
import com.example.myspbstuschedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGroupScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    operator fun invoke(groupId : Int, date : String) : Flow<Schedule>{
        return repository.getGroupSchedule(groupId, date)
    }
}