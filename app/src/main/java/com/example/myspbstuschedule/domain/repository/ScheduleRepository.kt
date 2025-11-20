package com.example.myspbstuschedule.domain.repository

import com.example.myspbstuschedule.domain.model.Group
import com.example.myspbstuschedule.domain.model.Schedule
import com.example.myspbstuschedule.domain.model.Teacher
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    fun getGroups(name : String) : Flow<List<Group>>

    fun getGroupSchedule(groupId : Int, date : String) : Flow<Schedule>

    fun getTeachers(name: String): Flow<List<Teacher>>

    fun getTeacherSchedule(teacherId: Int, date: String): Flow<Schedule>
}