package com.example.myspbstuschedule.domain.usecase

import com.example.myspbstuschedule.domain.model.Teacher
import com.example.myspbstuschedule.domain.repository.ScheduleRepository
import javax.inject.Inject

class GetTeachersUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(name : String) : List<Teacher>{
        return repository.getTeachers(name)
    }
}