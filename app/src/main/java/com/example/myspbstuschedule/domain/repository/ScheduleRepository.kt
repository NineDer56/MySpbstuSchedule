package com.example.myspbstuschedule.domain.repository

import com.example.myspbstuschedule.domain.model.Group
import com.example.myspbstuschedule.domain.model.Schedule
import com.example.myspbstuschedule.domain.model.Teacher

interface ScheduleRepository {

    suspend fun getGroups(name: String): List<Group>
    suspend fun getTeachers(name: String): List<Teacher>

    suspend fun getGroupsSchedule(id: Int, date: String): Schedule
    suspend fun getTeachersSchedule(id: Int, date: String): Schedule
}