package com.example.myspbstuschedule.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myspbstuschedule.data.datastore.SavedSettings
import com.example.myspbstuschedule.data.datastore.SettingsDataStore
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

    suspend fun getLastSelection(): SavedSettings? {
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

}