package com.example.myspbstuschedule.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// TODO Разобраться с Cascade

@Entity(
    tableName = "lesson",
    foreignKeys = [
        ForeignKey(
            entity = LessonTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["lessonTypeId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DayEntity::class,
            parentColumns = ["id"],
            childColumns = ["dayId"]
        )
    ],
    indices = [Index("lessonTypeId"), Index("dayId")]
)
data class LessonEntity(
    @PrimaryKey val id: Int,
    val subject: String,
    val timeStart: String,
    val timeEnd: String,
    val lessonTypeId: Int,
    val dayId: Int
)