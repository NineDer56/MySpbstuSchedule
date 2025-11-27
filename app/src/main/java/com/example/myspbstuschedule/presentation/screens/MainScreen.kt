package com.example.myspbstuschedule.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.myspbstuschedule.presentation.navigation.AppNavGraph
import com.example.myspbstuschedule.presentation.navigation.rememberNavigationState
import com.example.myspbstuschedule.presentation.screens.schedule.ScheduleScreen
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode
import com.example.myspbstuschedule.presentation.screens.selection.SelectionScreen

@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

    AppNavGraph(
        navHostController = navigationState.navHostController,

        selectionScreen = {
            SelectionScreen(
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 8.dp),
                onGroupItemClick = { groupId ->
                    navigationState.navigateToSchedule(SearchMode.GROUP, groupId)
                },
                onTeacherItemClick = { teacherId ->
                    navigationState.navigateToSchedule(SearchMode.TEACHER, teacherId)
                }
            )
        },

        scheduleScreen = { mode, id ->
            ScheduleScreen(
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 8.dp),
                id = id,
                mode = mode
            )
        }
    )
}