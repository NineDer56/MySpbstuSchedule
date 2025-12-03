package com.example.myspbstuschedule.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode


class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateToSelection(){
        navHostController.navigate(Routes.ROUTE_SELECTION){
            launchSingleTop = true
            popUpTo(Routes.ROUTE_SCHEDULE) {
                inclusive = true
            }
        }
        Log.d("Navigation", "select ${navHostController.currentBackStackEntry?.destination}")
    }

    fun navigateToSchedule(mode: SearchMode, id: Int) {
        navHostController.navigate(Routes.Schedule.getRoute(mode, id)) {
            launchSingleTop = true
            popUpTo(Routes.ROUTE_SELECTION) {
                inclusive = true
            }
        }
        Log.d("Navigation", "schedule ${navHostController.currentBackStackEntry?.destination}")
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember(navHostController) {
        NavigationState(navHostController)
    }
}