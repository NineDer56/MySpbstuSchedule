package com.example.myspbstuschedule.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "lesson_auditory_cross_ref",
    primaryKeys = ["lessonId", "auditoryId"],
    foreignKeys = [
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["id"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AuditoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["auditoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("auditoryId")]
)
data class LessonAuditoryCrossRef(
    val lessonId : Int,
    val auditoryId : Int
)
