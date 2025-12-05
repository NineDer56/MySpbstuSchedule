package com.example.myspbstuschedule.presentation.screens.selection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SelectionScreen(
    contentPadding: PaddingValues,
    onGroupItemClick: (groupId: Int, name: String) -> Unit,
    onTeacherItemClick: (teacherId: Int, name: String) -> Unit
) {
    val viewModel: SelectionViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize()
    ) {
        SelectionContent(
            modifier = Modifier
                .padding(contentPadding),
            onSearchClick = { name, mode -> viewModel.search(name, mode) },
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        ) {
            when (val currentState = state) {
                is SelectionState.Initial -> {
                    SelectionInitial(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                is SelectionState.Loading -> {
                    SelectionLoading(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                is SelectionState.Error -> {
                    SelectionError(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                is SelectionState.Content -> {
                    SelectionContentItems(
                        modifier = Modifier
                            .fillMaxSize(),
                        mode = currentState.mode,
                        groups = currentState.groups,
                        teachers = currentState.teachers,
                        onGroupItemClick = onGroupItemClick,
                        onTeacherItemClick = onTeacherItemClick
                    )
                }
            }
        }

    }

}