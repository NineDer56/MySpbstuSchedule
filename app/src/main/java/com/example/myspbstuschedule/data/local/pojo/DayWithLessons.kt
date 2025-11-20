package com.example.myspbstuschedule.data.local.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myspbstuschedule.data.local.entity.DayEntity
import com.example.myspbstuschedule.data.local.entity.LessonEntity

data class DayWithLessons(
    @Embedded val day : DayEntity,
    @Relation(
        entity = LessonEntity::class,
        parentColumn = "id",
        entityColumn = "dayId"
    )
    val lessons : List<LessonWithRelations>
)
