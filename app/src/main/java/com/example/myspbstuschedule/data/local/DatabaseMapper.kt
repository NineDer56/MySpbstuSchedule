package com.example.myspbstuschedule.data.local

import com.example.myspbstuschedule.data.local.entity.FacultyEntity
import com.example.myspbstuschedule.data.local.pojo.GroupWithFaculty
import com.example.myspbstuschedule.domain.model.Faculty
import com.example.myspbstuschedule.domain.model.Group

object DatabaseMapper {

    fun FacultyEntity.toDomain(): Faculty {
        return Faculty(
            id = id,
            name = name,
            abbr = abbr
        )
    }

    fun GroupWithFaculty.toDomain() : Group {
        return Group(
            id = group.id,
            name = group.name,
            level = group.level,
            faculty = facultyEntity.toDomain()
        )
    }

}