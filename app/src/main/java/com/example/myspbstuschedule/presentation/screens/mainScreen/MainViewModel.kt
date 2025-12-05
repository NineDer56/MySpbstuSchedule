package com.example.myspbstuschedule.presentation.screens.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myspbstuschedule.data.datastore.SavedSettings
import com.example.myspbstuschedule.data.datastore.SettingsDataStore
import com.example.myspbstuschedule.presentation.navigation.Routes
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : ViewModel() {

    private val _selectedNameState = MutableStateFlow<String>("")
    val selectedNameState = _selectedNameState.asStateFlow()

    private val _startDestinationState = MutableStateFlow<String?>(null)
    val startDestinationState = _startDestinationState.asStateFlow()


    init {
        viewModelScope.launch {
            val lastSelection = getLastSelection()
            if (lastSelection != null && lastSelection.mode == SearchMode.GROUP) {
                _startDestinationState.value = Routes.Schedule.getRoute(
                    mode = lastSelection.mode,
                    id = lastSelection.id
                )
            } else {
                _startDestinationState.value = Routes.Selection.route
            }
        }
    }

    private suspend fun getLastSelection(): SavedSettings? {
        val result = withContext(Dispatchers.IO) {
            settingsDataStore.getLastSelection()
        }
        result?.let {
            _selectedNameState.value = result.name
        }
        return result
    }

    fun onSelected(mode: SearchMode, id: Int, name: String) {
        viewModelScope.launch {
            _selectedNameState.value = name
            when (mode) {
                SearchMode.GROUP -> {
                    settingsDataStore.setLastSelectedGroup(id, name)
                    settingsDataStore.resetLastSelectedTeacher()
                }

                SearchMode.TEACHER -> {
                    settingsDataStore.setLastSelectedTeacher(id, name)
                    settingsDataStore.resetLastSelectedGroup()
                }
            }
        }
    }

    fun clearSelection() {
        viewModelScope.launch {
            _selectedNameState.value = ""
            settingsDataStore.resetLastSelectedGroup()
            settingsDataStore.resetLastSelectedTeacher()
        }
    }

}