package com.example.myspbstuschedule.presentation.screens.selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myspbstuschedule.domain.model.Group
import com.example.myspbstuschedule.domain.model.Teacher

@Composable
fun SelectionContentItems(
    modifier: Modifier = Modifier,
    mode: SearchMode,
    teachers: List<Teacher> = emptyList(),
    groups: List<Group> = emptyList()
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (mode == SearchMode.GROUP) {
            items(items = groups, key = { it.id }) { group ->
                GroupItem(group)
            }
        } else if (mode == SearchMode.TEACHER) {
            items(items = teachers, key = { it.id }) { teacher ->
                TeacherItem(teacher)
            }
        }
    }
}
