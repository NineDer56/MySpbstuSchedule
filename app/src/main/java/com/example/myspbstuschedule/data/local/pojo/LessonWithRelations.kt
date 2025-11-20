package com.example.myspbstuschedule.data.local.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.myspbstuschedule.data.local.entity.AuditoryEntity
import com.example.myspbstuschedule.data.local.entity.GroupEntity
import com.example.myspbstuschedule.data.local.entity.LessonAuditoryCrossRef
import com.example.myspbstuschedule.data.local.entity.LessonEntity
import com.example.myspbstuschedule.data.local.entity.LessonGroupCrossRef
import com.example.myspbstuschedule.data.local.entity.LessonTeacherCrossRef
import com.example.myspbstuschedule.data.local.entity.LessonTypeEntity
import com.example.myspbstuschedule.data.local.entity.TeacherEntity

data class LessonWithRelations(
    @Embedded val lesson : LessonEntity,

    @Relation(
        parentColumn = "lessonTypeId",
        entityColumn = "id"
    )
    val lessonType : LessonTypeEntity,

    @Relation(
        entity = GroupEntity::class,
        parentColumn = "id",    // LessonEntity.id
        entityColumn = "id",   // GroupEntity.id
        associateBy = Junction(
            value = LessonGroupCrossRef::class,
            parentColumn = "lessonId",
            entityColumn = "groupId"
        )
    )
    val groups : List<GroupWithFaculty>,


    @Relation(
        entity = TeacherEntity::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = LessonTeacherCrossRef::class,
            parentColumn = "lessonId",
            entityColumn = "teacherId"
        )
    )
    val teachers : List<TeacherEntity>,


    @Relation(
        entity = AuditoryEntity::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = LessonAuditoryCrossRef::class,
            parentColumn = "lessonId",
            entityColumn = "auditoryId"
        )
    )
    val auditories : List<AuditoryWithBuilding>
)
