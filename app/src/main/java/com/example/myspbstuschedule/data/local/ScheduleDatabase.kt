package com.example.myspbstuschedule.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myspbstuschedule.data.local.entity.AuditoryEntity
import com.example.myspbstuschedule.data.local.entity.BuildingEntity
import com.example.myspbstuschedule.data.local.entity.DayEntity
import com.example.myspbstuschedule.data.local.entity.FacultyEntity
import com.example.myspbstuschedule.data.local.entity.GroupEntity
import com.example.myspbstuschedule.data.local.entity.LessonAuditoryCrossRef
import com.example.myspbstuschedule.data.local.entity.LessonEntity
import com.example.myspbstuschedule.data.local.entity.LessonGroupCrossRef
import com.example.myspbstuschedule.data.local.entity.LessonTeacherCrossRef
import com.example.myspbstuschedule.data.local.entity.LessonTypeEntity
import com.example.myspbstuschedule.data.local.entity.TeacherEntity
import com.example.myspbstuschedule.data.local.entity.WeekEntity

@Database(
    entities = [
        BuildingEntity::class,
        AuditoryEntity::class,
        FacultyEntity::class,
        GroupEntity::class,
        TeacherEntity::class,
        LessonTypeEntity::class,
        WeekEntity::class,
        DayEntity::class,
        LessonEntity::class,
        LessonGroupCrossRef::class,
        LessonTeacherCrossRef::class,
        LessonAuditoryCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun dao() : ScheduleDao
}