package com.example.myspbstuschedule.presentation.navigation

import com.example.myspbstuschedule.presentation.screens.selection.SearchMode

sealed class Routes(val route : String) {

    data object Selection : Routes(ROUTE_SELECTION)

    data object Schedule : Routes(ROUTE_SCHEDULE) {
        private const val ROUTE_FOR_ARGS = "route_schedule"

        fun getRoute(mode : SearchMode, id : Int) : String{
            return "${ROUTE_FOR_ARGS}/${mode.name}/$id"
        }
    }


    companion object {
        const val ROUTE_SELECTION = "route_selection"

        const val KEY_ID = "key_id"
        const val KEY_MODE = "key_mode"
        const val ROUTE_SCHEDULE = "route_schedule/{$KEY_MODE}/{$KEY_ID}"
    }
}