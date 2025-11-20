package com.example.myspbstuschedule.data.local.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myspbstuschedule.data.local.entity.DayEntity
import com.example.myspbstuschedule.data.local.entity.WeekEntity

data class WeekWithDays(
    @Embedded val week : WeekEntity,
    @Relation(
        entity = DayEntity::class,
        parentColumn = "id",
        entityColumn = "weekId"
    )
    val days : List<DayWithLessons>
)
