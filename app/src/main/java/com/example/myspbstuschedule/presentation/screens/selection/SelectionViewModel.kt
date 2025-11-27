package com.example.myspbstuschedule.presentation.screens.selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myspbstuschedule.domain.usecase.GetGroupsUseCase
import com.example.myspbstuschedule.domain.usecase.GetTeachersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor(
    private val getGroupsUseCase: GetGroupsUseCase,
    private val getTeachersUseCase: GetTeachersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<SelectionState>(SelectionState.Initial)
    val state = _state.asStateFlow()


    fun search(name: String, mode: SearchMode) {
        _state.value = SelectionState.Loading

        viewModelScope.launch {
            when (mode) {
                SearchMode.GROUP -> handleGroups(name)
                SearchMode.TEACHER -> handleTeachers(name)
            }
        }
    }

    private suspend fun handleGroups(name: String) {
        getGroupsUseCase(name)
            .catch { e ->
                _state.value = SelectionState.Error(
                    e.message ?: "Неизвестная ошибка"
                )
            }
            .collect { result ->
                _state.value = SelectionState.Content(
                    groups = result,
                    teachers = emptyList(),
                    mode = SearchMode.GROUP
                )
            }
    }

    private suspend fun handleTeachers(name: String) {
        getTeachersUseCase(name)
            .catch { e ->
                _state.value = SelectionState.Error(
                    e.message ?: "Неизвестная ошибка"
                )
            }
            .collect { result ->
                _state.value = SelectionState.Content(
                    groups = emptyList(),
                    teachers = result,
                    mode = SearchMode.TEACHER
                )
            }
    }
}