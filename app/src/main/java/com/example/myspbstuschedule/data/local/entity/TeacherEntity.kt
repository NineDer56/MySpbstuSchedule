package com.example.myspbstuschedule.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "teacher"
)
data class TeacherEntity(
    @PrimaryKey val id : Int,
    val name : String,
    val chair : String
)
