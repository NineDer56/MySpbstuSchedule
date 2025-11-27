package com.example.myspbstuschedule.data

import com.example.myspbstuschedule.data.local.DatabaseMapper
import com.example.myspbstuschedule.data.local.ScheduleDao
import com.example.myspbstuschedule.data.network.api.NetworkApi
import com.example.myspbstuschedule.data.network.mapper.NetworkMapper
import com.example.myspbstuschedule.domain.model.Group
import com.example.myspbstuschedule.domain.model.Schedule
import com.example.myspbstuschedule.domain.model.Teacher
import com.example.myspbstuschedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
    private val networkMapper : NetworkMapper,
    private val dao: ScheduleDao,
    private val databaseMapper : DatabaseMapper
) : ScheduleRepository {

    override fun getGroups(name: String): Flow<List<Group>> {
        return flow {
            val groups = withContext(Dispatchers.IO) {
                networkApi.getGroups(name).groups.map { networkMapper.mapGroupNwModelToEntity(it) }
            }
            emit(groups)
        }
    }

    override fun getGroupSchedule(groupId: Int, date: String): Flow<Schedule> {
        TODO("Not yet implemented")
    }

    override fun getTeachers(name: String): Flow<List<Teacher>> {
        return flow{
            val teachers = withContext(Dispatchers.IO){
                networkApi.getTeachers(name).teachers.map {networkMapper.mapTeacherNwModelToEntity(it)}
            }
            emit(teachers)
        }
    }

    override fun getTeacherSchedule(teacherId: Int, date: String): Flow<Schedule> {
        TODO("Not yet implemented")
    }
}