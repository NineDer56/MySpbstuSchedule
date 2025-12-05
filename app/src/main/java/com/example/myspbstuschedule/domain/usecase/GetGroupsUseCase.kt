package com.example.myspbstuschedule.domain.usecase

import com.example.myspbstuschedule.domain.model.Group
import com.example.myspbstuschedule.domain.repository.ScheduleRepository
import javax.inject.Inject

class GetGroupsUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(name : String) : List<Group>{
        return repository.getGroups(name)
    }
}