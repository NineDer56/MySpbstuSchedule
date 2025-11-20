package com.example.myspbstuschedule.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson_type")
data class LessonTypeEntity(
    @PrimaryKey val id : Int,
    val name : String,
    val abbr : String
)
