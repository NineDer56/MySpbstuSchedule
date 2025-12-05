package com.example.myspbstuschedule.data.datastore

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object SettingsKeys {
    val LAST_SELECTED_GROUP_ID = intPreferencesKey("last_selected_group_id")
    val LAST_SELECTED_TEACHER_ID = intPreferencesKey("last_selected_teacher_id")
    val LAST_SELECTED_NAME = stringPreferencesKey("last_selected_name")
}