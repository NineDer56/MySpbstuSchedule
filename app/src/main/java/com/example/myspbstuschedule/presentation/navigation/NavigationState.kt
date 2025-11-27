package com.example.myspbstuschedule.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode


class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateToSchedule(mode : SearchMode, id : Int){
        navHostController.navigate(Routes.Schedule.getRoute(mode, id))
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
) : NavigationState{
    return remember(navHostController) {
        NavigationState(navHostController)
    }
}