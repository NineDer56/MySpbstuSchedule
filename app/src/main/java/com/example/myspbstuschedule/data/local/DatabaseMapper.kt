package com.example.myspbstuschedule.data.local

import com.example.myspbstuschedule.data.local.entity.ScheduleEntity
import com.example.myspbstuschedule.domain.model.Schedule
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode
import com.google.gson.Gson


object DatabaseMapper {

    fun ScheduleEntity.toDomain() : Schedule{
        // TODO нормально ли что создаю GSON или нарушаю DI
        val schedule = Gson().fromJson(scheduleJson, Schedule::class.java)
        return schedule
    }

    fun Schedule.toEntity(mode : SearchMode, id : Int, updatedAt : Long) : ScheduleEntity{
        val scheduleEntity = ScheduleEntity(
            mode = mode.name,
            id = id,
            dateStart = week.dateStart,
            dateEnd = week.dateEnd,
            updatedAt = updatedAt,
            scheduleJson = Gson().toJson(this)
        )
        return scheduleEntity
    }
}