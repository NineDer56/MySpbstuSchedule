package com.example.myspbstuschedule.data.local.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myspbstuschedule.data.local.entity.AuditoryEntity
import com.example.myspbstuschedule.data.local.entity.BuildingEntity
import com.example.myspbstuschedule.domain.model.Auditory

data class AuditoryWithBuilding(
    @Embedded val auditory: AuditoryEntity,
    @Relation(
        parentColumn = "buildingId",
        entityColumn = "id"
    )
    val building : BuildingEntity
)
