package com.example.myspbstuschedule.presentation.screens.selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myspbstuschedule.domain.usecase.GetGroupsUseCase
import com.example.myspbstuschedule.domain.usecase.GetTeachersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor(
    private val getGroupsUseCase: GetGroupsUseCase,
    private val getTeachersUseCase: GetTeachersUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<SelectionState>(SelectionState.Initial)
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    fun search(name: String, mode: SearchMode) {
        _state.value = SelectionState.Loading

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            try {
                when (mode) {
                    SearchMode.GROUP -> handleGroups(name)
                    SearchMode.TEACHER -> handleTeachers(name)
                }
            } catch (e: Exception) {
                _state.value = SelectionState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }

    private suspend fun handleGroups(name: String) {
        val groups = getGroupsUseCase(name)
        _state.value = SelectionState.Content(
            groups = groups,
            teachers = emptyList(),
            mode = SearchMode.GROUP
        )
    }

    private suspend fun handleTeachers(name: String) {
        val teachers = getTeachersUseCase(name)
        _state.value = SelectionState.Content(
            groups = emptyList(),
            teachers = teachers,
            mode = SearchMode.TEACHER
        )
    }
}