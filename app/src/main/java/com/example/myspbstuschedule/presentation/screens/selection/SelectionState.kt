package com.example.myspbstuschedule.presentation.screens.selection

import com.example.myspbstuschedule.domain.model.Group
import com.example.myspbstuschedule.domain.model.Teacher

sealed class SelectionState {

    data object Initial : SelectionState()

    data object Loading : SelectionState()

    data class Error(
        val message: String
    ) : SelectionState()

    data class Content(
        val groups: List<Group>,
        val teachers: List<Teacher>,
        val mode: SearchMode
    ) : SelectionState()
}