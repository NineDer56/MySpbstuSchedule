package com.example.myspbstuschedule.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "student_group",
    foreignKeys = [
        ForeignKey(
            entity = FacultyEntity::class,
            parentColumns = ["id"],
            childColumns = ["facultyId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("facultyId")]
)
data class GroupEntity(
    @PrimaryKey val id : Int,
    val name : String,
    val level : Int,
    val facultyId : Int
)
