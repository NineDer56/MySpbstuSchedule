package com.example.myspbstuschedule.data

import android.util.Log
import com.example.myspbstuschedule.data.local.DatabaseMapper
import com.example.myspbstuschedule.data.local.ScheduleDao
import com.example.myspbstuschedule.data.network.api.NetworkApi
import com.example.myspbstuschedule.data.network.mapper.NetworkMapper.toApiRequest
import com.example.myspbstuschedule.data.network.mapper.NetworkMapper.toDbRequest
import com.example.myspbstuschedule.data.network.mapper.NetworkMapper.toDomain
import com.example.myspbstuschedule.domain.model.Group
import com.example.myspbstuschedule.domain.model.Schedule
import com.example.myspbstuschedule.domain.model.Teacher
import com.example.myspbstuschedule.domain.repository.ScheduleRepository
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
    private val dao: ScheduleDao,
    private val dbMapper: DatabaseMapper
) : ScheduleRepository {

    override suspend fun getGroups(name: String): List<Group> {
        return withContext(Dispatchers.IO) {
            networkApi.getGroups(name).groups.map { it.toDomain() }
        }
    }

    override suspend fun getTeachers(name: String): List<Teacher> {
        return withContext(Dispatchers.IO) {
            networkApi.getTeachers(name).teachers.map {
                it.toDomain()
            }
        }
    }

    override suspend fun getGroupsSchedule(id: Int, date: String): Schedule {

        val cached = dao.getSchedule(SearchMode.GROUP.name, id, date.toDbRequest())
        Log.d("Test", "repo getGroupSchedule cached: $cached")
        if (cached != null) {
            return dbMapper.scheduleEntityToDomain(cached)
        }

        val scheduleNwModel = networkApi.getGroupSchedule(id, date.toApiRequest())
        val schedule = scheduleNwModel.toDomain()
        val scheduleEntity = dbMapper.scheduleToEntity(
            schedule = schedule,
            mode = SearchMode.GROUP,
            id = id
        )

        Log.d("Test", "repo getGroupSchedule schedule from api to entity: $scheduleEntity")
        dao.upsertSchedule(scheduleEntity)

        return schedule
    }

    override suspend fun getTeachersSchedule(id: Int, date: String): Schedule {

        val cached = dao.getSchedule(SearchMode.TEACHER.name, id, date.toDbRequest())
        Log.d("Test", "repo getTeachersSchedule cached: $cached")
        if (cached != null) {
            return dbMapper.scheduleEntityToDomain(cached)
        }

        val scheduleNwModel = networkApi.getTeacherSchedule(id, date.toApiRequest())
        val schedule = scheduleNwModel.toDomain()
        val scheduleEntity = dbMapper.scheduleToEntity(
            schedule = schedule,
            mode = SearchMode.TEACHER,
            id = id
        )
        Log.d("Test", "repo getTeachersSchedule schedule from api to entity: $scheduleEntity")
        dao.upsertSchedule(scheduleEntity)

        return schedule
    }
}