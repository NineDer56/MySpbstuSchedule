package com.example.myspbstuschedule.presentation.screens.selection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myspbstuschedule.domain.model.Teacher
import com.example.myspbstuschedule.presentation.theme.MySpbstuScheduleTheme

@Composable
fun TeacherItem(
    teacher: Teacher,
    onTeacherItemClick: (teacherId: Int, name: String) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onTeacherItemClick(teacher.id, teacher.name) },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = teacher.name,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 20.sp
            )
            Text(
                text = teacher.chair,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp,
                lineHeight = 20.sp
            )
        }
    }

}


@Composable
@Preview
fun TeacherItemPreview() {
    MySpbstuScheduleTheme(
        darkTheme = true
    ) {
        TeacherItem(
            Teacher(
                1,
                "Хахина Анна Михайловна",
                "51/03 Высшая школа программной инженерии"
            ),
            { a, b -> }
        )
    }
}