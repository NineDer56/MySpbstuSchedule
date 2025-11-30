package com.example.myspbstuschedule.data

import android.util.Log
import com.example.myspbstuschedule.data.local.DatabaseMapper.toDomain
import com.example.myspbstuschedule.data.local.ScheduleDao
import com.example.myspbstuschedule.data.local.entity.ScheduleEntity
import com.example.myspbstuschedule.data.network.api.NetworkApi
import com.example.myspbstuschedule.data.network.mapper.NetworkMapper
import com.example.myspbstuschedule.data.network.mapper.NetworkMapper.toApiRequest
import com.example.myspbstuschedule.data.network.mapper.NetworkMapper.toDbRequest
import com.example.myspbstuschedule.domain.model.Group
import com.example.myspbstuschedule.domain.model.Schedule
import com.example.myspbstuschedule.domain.model.Teacher
import com.example.myspbstuschedule.domain.repository.ScheduleRepository
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
    private val networkMapper: NetworkMapper,
    private val dao: ScheduleDao
) : ScheduleRepository {

    override suspend fun getGroups(name: String): List<Group> {
        return withContext(Dispatchers.IO) {
            networkApi.getGroups(name).groups.map { networkMapper.mapGroupNwModelToEntity(it) }
        }
    }

    override suspend fun getTeachers(name: String): List<Teacher> {
        return withContext(Dispatchers.IO) {
            networkApi.getTeachers(name).teachers.map {
                networkMapper.mapTeacherNwModelToEntity(it)
            }
        }
    }

    suspend fun getGroupsSchedule(id: Int, date: String): Schedule {

        val cached = dao.getSchedule(SearchMode.GROUP.name, id, date.toDbRequest())
        Log.d("Test", "repo getGroupSchedule cached: $cached")
        if (cached != null) {
            return cached.toDomain()
        }

        val scheduleNwModel = networkApi.getGroupSchedule(id, date.toApiRequest())
        val schedule = NetworkMapper.mapScheduleNwModelToEntity(scheduleNwModel)
        val scheduleEntity = ScheduleEntity(
            mode = SearchMode.GROUP.name,
            id = id,
            dateStart = schedule.week.dateStart,
            dateEnd = schedule.week.dateEnd,
            updatedAt = System.currentTimeMillis(),
            scheduleJson = Gson().toJson(schedule)
        )
        Log.d("Test", "repo getGroupSchedule schedule from api to entity: $scheduleEntity")
        dao.upsertSchedule(scheduleEntity)

        return schedule
    }

    suspend fun getTeachersSchedule(id: Int, date: String): Schedule {

        val cached = dao.getSchedule(SearchMode.TEACHER.name, id, date.toDbRequest())
        Log.d("Test", "repo getTeachersSchedule cached: $cached")
        if (cached != null) {
            return cached.toDomain()
        }

        val scheduleNwModel = networkApi.getTeacherSchedule(id, date.toApiRequest())
        val schedule = NetworkMapper.mapScheduleNwModelToEntity(scheduleNwModel)
        val scheduleEntity = ScheduleEntity(
            mode = SearchMode.TEACHER.name,
            id = id,
            dateStart = schedule.week.dateStart,
            dateEnd = schedule.week.dateEnd,
            updatedAt = System.currentTimeMillis(),
            scheduleJson = Gson().toJson(schedule)
        )
        Log.d("Test", "repo getTeachersSchedule schedule from api to entity: $scheduleEntity")
        dao.upsertSchedule(scheduleEntity)

        return schedule
    }
}