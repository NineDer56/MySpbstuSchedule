package com.example.myspbstuschedule.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myspbstuschedule.data.local.entity.ScheduleEntity


@Database(
    entities = [
        ScheduleEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun dao() : ScheduleDao
}