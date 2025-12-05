package com.example.myspbstuschedule.presentation.screens.schedule

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myspbstuschedule.domain.model.Lesson

@Composable
fun ScheduleContentItems(
    modifier: Modifier = Modifier,
    lessons: List<Lesson>
) {
    LazyColumn(
        modifier = modifier
    ) {
        lessons.forEachIndexed { index, lesson ->
            if (index == 0) {
                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
            item {
                ScheduleItem(
                    lesson = lesson,
                    index = index + 1
                )
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }
}
