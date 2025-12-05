package com.example.myspbstuschedule.presentation.screens.mainScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myspbstuschedule.presentation.navigation.AppNavGraph
import com.example.myspbstuschedule.presentation.navigation.Routes
import com.example.myspbstuschedule.presentation.navigation.rememberNavigationState
import com.example.myspbstuschedule.presentation.screens.schedule.ScheduleScreen
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode
import com.example.myspbstuschedule.presentation.screens.selection.SelectionScreen

@Composable
fun MainScreen(
    startDestination : String
) {

    val viewModel: MainViewModel = hiltViewModel()
    val navigationState = rememberNavigationState()
    val selectedNameState by viewModel.selectedNameState.collectAsStateWithLifecycle()

    val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()
    val destination = navBackStackEntry.value?.destination?.route

    LaunchedEffect(Unit) {
        val lastSelection = viewModel.getLastSelection()
        Log.d("Navigation", "last selection $lastSelection")
        lastSelection?.let {
            when (it.mode) {
                SearchMode.GROUP -> {
                    navigationState.navigateToSchedule(SearchMode.GROUP, it.id)
                    Log.d("Navigation", "Navigate to group ${it.id}")
                }

                SearchMode.TEACHER -> {
                    navigationState.navigateToSchedule(SearchMode.TEACHER, it.id)
                    Log.d("Navigation", "Navigate to teacher ${it.id}")
                }
            }
        }
    }

    Scaffold(
        topBar = {
            when (destination) {
                Routes.ROUTE_SELECTION -> {
                    TopAppBarForSelectionScreen()
                }

                Routes.ROUTE_SCHEDULE -> {
                    TopAppBarForScheduleScreen(
                        name = selectedNameState,
                        onIconClick = {
                            navigationState.navigateToSelection()
                            viewModel.clearSelection()
                        }
                    )
                }

                else -> TopAppBarForSelectionScreen()
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            modifier = Modifier
                .consumeWindowInsets(paddingValues)
                .padding(paddingValues),

            startDestination = startDestination,

            navHostController = navigationState.navHostController,

            selectionScreen = {
                SelectionScreen(
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    onGroupItemClick = { groupId, name ->
                        Log.d("Navigation", "selected group $groupId")
                        viewModel.onSelected(SearchMode.GROUP, groupId, name)
                        navigationState.navigateToSchedule(SearchMode.GROUP, groupId)
                    },
                    onTeacherItemClick = { teacherId, name ->
                        Log.d("Navigation", "selected teacher $teacherId")
                        viewModel.onSelected(SearchMode.TEACHER, teacherId, name)
                        navigationState.navigateToSchedule(SearchMode.TEACHER, teacherId)
                    }
                )
            },

            scheduleScreen = {
                ScheduleScreen(
                    contentPadding = PaddingValues(horizontal = 8.dp)
                )
            }
        )

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarForSelectionScreen() {
    TopAppBar(
        title = {
            Text(
                text = "Schedule",
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            scrolledContainerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarForScheduleScreen(
    name: String,
    onIconClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RectangleShape
    ) {
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = "Schedule",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 32.sp
                    )
                    Text(
                        modifier = Modifier.padding(start = 12.dp),
                        text = name,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                scrolledContainerColor = MaterialTheme.colorScheme.primary
            ),
            actions = {
                IconButton(
                    onClick = onIconClick
                ) {
                    Icon(
                        imageVector = Icons.Filled.Create,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
        )

    }
}
