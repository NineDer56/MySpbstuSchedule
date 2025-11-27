package com.example.myspbstuschedule.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myspbstuschedule.presentation.screens.selection.SearchMode

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navHostController : NavHostController,
    selectionScreen : @Composable () -> Unit,
    scheduleScreen : @Composable (mode : SearchMode, id : Int) -> Unit,
){
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Routes.Selection.route
    ) {

        composable(Routes.Selection.route) {
            selectionScreen()
        }

        composable(
            route = Routes.Schedule.route,
            arguments = listOf(
                navArgument(Routes.KEY_MODE) {type = NavType.StringType},
                navArgument(Routes.KEY_ID) {type = NavType.IntType}
            )
        ){ navBackStackEntry ->
            val modeArg = navBackStackEntry.arguments?.getString(Routes.KEY_MODE)
            val mode = modeArg?.let {
                SearchMode.valueOf(it)
            }  ?: SearchMode.GROUP

            val id = navBackStackEntry.arguments?.getInt(Routes.KEY_ID) ?: 0
            scheduleScreen(mode, id)
        }
    }

}