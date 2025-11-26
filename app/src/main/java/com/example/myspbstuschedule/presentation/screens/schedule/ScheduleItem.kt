package com.example.myspbstuschedule.presentation.screens.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myspbstuschedule.domain.model.Auditory
import com.example.myspbstuschedule.domain.model.Building
import com.example.myspbstuschedule.domain.model.Lesson
import com.example.myspbstuschedule.domain.model.LessonType
import com.example.myspbstuschedule.domain.model.Teacher
import com.example.myspbstuschedule.ui.theme.MySpbstuScheduleTheme

@Composable
fun ScheduleItem(
    lesson: Lesson,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onBackground
        )

    ) {

        Spacer(modifier = Modifier.padding(8.dp))

        Header(lesson = lesson)

        Spacer(modifier = Modifier.padding(8.dp))

        SubjectInformation(lesson = lesson)

        Spacer(modifier = Modifier.padding(4.dp))
    }
}


@Composable
private fun Header(
    lesson: Lesson
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 6.dp, bottomEnd = 6.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 12.dp, vertical = 8.dp)

        ) {
            Text(
                text = "1",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "${lesson.timeStart} - ${lesson.timeEnd}",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.CenterEnd
        ){
            Text(
                modifier = Modifier.width(140.dp),
                text = "${lesson.auditories[0].building.name}, ауд.${lesson.auditories[0].name}",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.End
            )
        }


        Spacer(modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun SubjectInformation(
    lesson: Lesson
) {
    Column(
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Text(
            text = lesson.subject,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = lesson.lessonType.name,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = lesson.teachers[0].name,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}


@Preview
@Composable
fun ScheduleItemPreview() {
    MySpbstuScheduleTheme(
        darkTheme = true
    ) {
        ScheduleItem(
            Lesson(
                subject = "Администрирование информационных систем",
                timeStart = "12:00",
                timeEnd = "13:40",
                lessonType = LessonType(1, "Лабораторные", "Лаб"),
                groups = emptyList(),
                teachers = listOf(Teacher(1, "Брык Иван Юрьевич", "")),
                auditories = listOf(Auditory(1, "215", Building(1, "11-й учебный корпус", "11 к.")))
            )
        )
    }
}