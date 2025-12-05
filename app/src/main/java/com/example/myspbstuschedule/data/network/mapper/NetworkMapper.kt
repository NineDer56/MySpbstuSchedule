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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object NetworkMapper {

    fun ScheduleNwModel.toDomain(): Schedule {
        return Schedule(
            week = week.toDomain(),
            days = days.map { it.toDomain() }
        )
    }

    private fun WeekNwModel.toDomain(): Week {
        return Week(
            dateStart = dateStart,
            dateEnd = dateEnd,
            idOdd = idOdd
        )
    }

    private fun DayNwModel.toDomain(): Day {
        return Day(
            weekday = weekday,
            date = date,
            lessons = lessons.map { it.toDomain() }
        )
    }

    private fun LessonNwModel.toDomain(): Lesson {
        return Lesson(
            subject = subject,
            timeStart = timeStart,
            timeEnd = timeEnd,
            lessonType = lessonType.toDomain(),
            groups = groups.map { it.toDomain() },
            teachers = teachers?.map { it.toDomain() } ?: listOf(Teacher(-1, "Не знаю кто", "")),
            auditories = auditories.map { it.toDomain() }
        )
    }

    private fun LessonTypeNwModel.toDomain(): LessonType {
        return LessonType(
            id = id,
            name = name,
            abbr = abbr
        )
    }

    fun GroupNwModel.toDomain(): Group {
        return Group(
            id = id,
            name = name,
            faculty = faculty.toDomain(),
            level = level
        )
    }

    private fun FacultyNwModel.toDomain(): Faculty {
        return Faculty(
            id = id,
            name = name,
            abbr = abbr
        )
    }

    fun TeacherNwModel.toDomain(): Teacher {
        return Teacher(
            id = id,
            name = name,
            chair = chair
        )
    }

    private fun AuditoryNwModel.toDomain(): Auditory {
        return Auditory(
            id = id,
            name = name,
            building = building.toDomain()
        )
    }

    private fun BuildingNwModel.toDomain(): Building {
        return Building(
            id = id,
            name = name,
            abbr = abbr
        )
    }

    fun String.toApiRequest(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(this)
        val formatted = date.format(formatter)
        return formatted
    }

    fun String.toDbRequest(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val date = LocalDate.parse(this)
        val formatted = date.format(formatter)
        return formatted
    }
}