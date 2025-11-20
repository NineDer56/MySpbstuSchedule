package com.example.myspbstuschedule.domain.usecase

import com.example.myspbstuschedule.domain.model.Teacher
import com.example.myspbstuschedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeachersUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    operator fun invoke(name : String) : Flow<List<Teacher>>{
        return repository.getTeachers(name)
    }
}