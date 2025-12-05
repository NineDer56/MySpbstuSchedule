package com.example.myspbstuschedule.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SettingsDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun getLastSelection(): SavedSettings? {
        val prefs = context.settingsDataStore.data.first()

        val groupId = prefs[SettingsKeys.LAST_SELECTED_GROUP_ID]
        val teacherId = prefs[SettingsKeys.LAST_SELECTED_TEACHER_ID]
        val name = prefs[SettingsKeys.LAST_SELECTED_NAME]
        Log.d("Navigation", "groupId $groupId, teacherId $teacherId, name $name")
        val result = when {
            groupId != null && name != null -> {
                SavedSettings(
                    mode = SearchMode.GROUP,
                    id = groupId,
                    name = name
                )
            }

            teacherId != null && name != null -> {
                SavedSettings(
                    mode = SearchMode.TEACHER,
                    id = teacherId,
                    name = name
                )
            }

            else -> null
        }

        return result
    }

    suspend fun setLastSelectedGroup(id: Int, name: String) {
        context.settingsDataStore.edit { preferences ->
            preferences[SettingsKeys.LAST_SELECTED_GROUP_ID] = id
            preferences[SettingsKeys.LAST_SELECTED_NAME] = name
        }
    }

    suspend fun resetLastSelectedGroup() {
        context.settingsDataStore.edit { preferences ->
            preferences.remove(SettingsKeys.LAST_SELECTED_GROUP_ID)
        }
    }


    suspend fun setLastSelectedTeacher(id: Int, name: String) {
        context.settingsDataStore.edit { preferences ->
            preferences[SettingsKeys.LAST_SELECTED_TEACHER_ID] = id
            preferences[SettingsKeys.LAST_SELECTED_NAME] = name
        }
    }

    suspend fun resetLastSelectedTeacher() {
        context.settingsDataStore.edit { preferences ->
            preferences.remove(SettingsKeys.LAST_SELECTED_TEACHER_ID)
        }
    }
}