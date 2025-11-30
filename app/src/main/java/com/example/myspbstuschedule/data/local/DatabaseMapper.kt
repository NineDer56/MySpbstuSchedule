package com.example.myspbstuschedule.data.local

import com.example.myspbstuschedule.data.local.entity.ScheduleEntity
import com.example.myspbstuschedule.domain.model.Schedule
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode
import com.google.gson.Gson
import javax.inject.Inject


class DatabaseMapper @Inject constructor(
    private val gson: Gson
) {

    fun scheduleEntityToDomain(scheduleEntity: ScheduleEntity): Schedule {
        val schedule = gson.fromJson(scheduleEntity.scheduleJson, Schedule::class.java)
        return schedule
    }

    fun scheduleToEntity(schedule: Schedule, mode: SearchMode, id: Int): ScheduleEntity {
        val scheduleEntity = ScheduleEntity(
            mode = mode.name,
            id = id,
            dateStart = schedule.week.dateStart,
            dateEnd = schedule.week.dateEnd,
            updatedAt = System.currentTimeMillis(),
            scheduleJson = gson.toJson(schedule)
        )
        return scheduleEntity
    }
}