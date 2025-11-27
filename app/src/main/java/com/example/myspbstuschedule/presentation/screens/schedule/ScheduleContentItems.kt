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
        items(items = lessons, key = { "${it.subject}${it.timeStart}" }) { lesson ->
            ScheduleItem(
                lesson = lesson
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}
