package com.example.myspbstuschedule.data.network.mapper

import com.example.myspbstuschedule.data.network.dto.AuditoryNwModel
import com.example.myspbstuschedule.data.network.dto.BuildingNwModel
import com.example.myspbstuschedule.data.network.dto.DayNwModel
import com.example.myspbstuschedule.data.network.dto.FacultyNwModel
import com.example.myspbstuschedule.data.network.dto.GroupNwModel
import com.example.myspbstuschedule.data.network.dto.LessonNwModel
import com.example.myspbstuschedule.data.network.dto.LessonTypeNwModel
import com.example.myspbstuschedule.data.network.dto.ScheduleNwModel
import com.example.myspbstuschedule.data.network.dto.TeacherNwModel
import com.example.myspbstuschedule.data.network.dto.WeekNwModel
import com.example.myspbstuschedule.domain.model.Auditory
import com.example.myspbstuschedule.domain.model.Building
import com.example.myspbstuschedule.domain.model.Day
import com.example.myspbstuschedule.domain.model.Faculty
import com.example.myspbstuschedule.domain.model.Group
import com.example.myspbstuschedule.domain.model.Lesson
import com.example.myspbstuschedule.domain.model.LessonType
import com.example.myspbstuschedule.domain.model.Schedule
import com.example.myspbstuschedule.domain.model.Teacher
import com.example.myspbstuschedule.domain.model.Week

object NetworkMapper {
    fun mapScheduleNwModelToEntity(nwModel: ScheduleNwModel) : Schedule {
        return Schedule(
            week = mapWeekNwModelToEntity(nwModel.week),
            days = nwModel.days.map { mapDayNwModelToEntity(it) }
        )
    }

    private fun mapWeekNwModelToEntity(nwModel: WeekNwModel) : Week {
        return Week(
            dateStart = nwModel.dateStart,
            dateEnd = nwModel.dateEnd,
            idOdd = nwModel.idOdd
        )
    }

    private fun mapDayNwModelToEntity(nwModel: DayNwModel) : Day {
        return Day(
            weekday = nwModel.weekday,
            date = nwModel.date,
            lessons = nwModel.lessons.map { mapLessonNwModelToEntity(it) }
        )
    }

    private fun mapLessonNwModelToEntity(nwModel: LessonNwModel) : Lesson {
        return Lesson(
            subject = nwModel.subject,
            timeStart = nwModel.timeStart,
            timeEnd = nwModel.timeEnd,
            lessonType = mapLessonTypeNwModelToEntity(nwModel.lessonType),
            groups = nwModel.groups.map { mapGroupNwModelToEntity(it) },
            teachers = nwModel.teachers?.map { mapTeacherNwModelToEntity(it) } ?: listOf(Teacher(-1, "Не знаю кто", "")),
            auditories = nwModel.auditories.map { mapAuditoryNwModelToEntity(it) }
        )
    }

    private fun mapLessonTypeNwModelToEntity(nwModel: LessonTypeNwModel) : LessonType {
        return LessonType(
            id = nwModel.id,
            name = nwModel.name,
            abbr = nwModel.abbr
        )
    }

    fun mapGroupNwModelToEntity(nwModel: GroupNwModel) : Group {
        return Group(
            id = nwModel.id,
            name = nwModel.name,
            faculty = mapFacultyNwModelToEntity(nwModel.faculty),
            level = nwModel.level
        )
    }

    private fun mapFacultyNwModelToEntity(nwModel: FacultyNwModel) : Faculty {
        return Faculty(
            id = nwModel.id,
            name = nwModel.name,
            abbr = nwModel.abbr
        )
    }

    fun mapTeacherNwModelToEntity(nwModel : TeacherNwModel) : Teacher{
        return Teacher(
            id = nwModel.id,
            name = nwModel.name,
            chair = nwModel.chair
        )
    }

    private fun mapAuditoryNwModelToEntity(nwModel: AuditoryNwModel) : Auditory {
        return Auditory(
            id = nwModel.id,
            name = nwModel.name,
            building = mapBuildingNwModelToEntity(nwModel.building)
        )
    }

    private fun mapBuildingNwModelToEntity(nwModel: BuildingNwModel) : Building {
        return Building(
            id = nwModel.id,
            name = nwModel.name,
            abbr = nwModel.abbr
        )
    }
}