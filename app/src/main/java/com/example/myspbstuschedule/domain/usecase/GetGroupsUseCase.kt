package com.example.myspbstuschedule.domain.usecase

import com.example.myspbstuschedule.domain.model.Group
import com.example.myspbstuschedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGroupsUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    operator fun invoke(name : String) : Flow<List<Group>>{
        return repository.getGroups(name)
    }
}