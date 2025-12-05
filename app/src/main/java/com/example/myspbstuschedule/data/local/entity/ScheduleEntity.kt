package com.example.myspbstuschedule.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "schedule_entity",
    primaryKeys = [
        "mode", "id", "date_start"
    ]
)
data class ScheduleEntity(
    @ColumnInfo(name = "mode")
    val mode: String,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "date_start")
    val dateStart: String,
    @ColumnInfo(name = "date_end")
    val dateEnd: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt : Long,
    @ColumnInfo(name = "schedule_json")
    val scheduleJson: String
)