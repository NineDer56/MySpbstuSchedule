package com.example.myspbstuschedule.data.datastore

import com.example.myspbstuschedule.presentation.screens.selection.SearchMode

data class SavedSettings(
    val mode: SearchMode,
    val id: Int,
    val name: String
)
