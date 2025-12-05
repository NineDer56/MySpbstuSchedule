package com.example.myspbstuschedule.presentation.screens.selection

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
    groups: List<Group> = emptyList(),
    onGroupItemClick: (groupId: Int, name: String) -> Unit,
    onTeacherItemClick: (teacherId: Int, name: String) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        if (mode == SearchMode.GROUP) {
            groups.forEachIndexed { index, group ->
                if (index == 0) {
                    item {
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                }
                item {
                    GroupItem(
                        group = group,
                        onGroupItemClick = onGroupItemClick
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        } else if (mode == SearchMode.TEACHER) {
            teachers.forEachIndexed { index, teacher ->
                if (index == 0) {
                    item {
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                }
                item {
                    TeacherItem(
                        teacher = teacher,
                        onTeacherItemClick = onTeacherItemClick
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}
