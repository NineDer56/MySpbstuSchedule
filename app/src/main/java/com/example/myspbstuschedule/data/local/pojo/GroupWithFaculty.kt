package com.example.myspbstuschedule.data.local.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myspbstuschedule.data.local.entity.FacultyEntity
import com.example.myspbstuschedule.data.local.entity.GroupEntity

// TODO почему faculty Это parentColumn

data class GroupWithFaculty(
    @Embedded val group : GroupEntity,
    @Relation(
        parentColumn = "facultyId",
        entityColumn = "id"
    )
    val facultyEntity: FacultyEntity
)
