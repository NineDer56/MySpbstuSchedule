package com.example.myspbstuschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.myspbstuschedule.presentation.navigation.Routes
import com.example.myspbstuschedule.presentation.screens.mainScreen.MainScreen
import com.example.myspbstuschedule.presentation.screens.mainScreen.MainViewModel
import com.example.myspbstuschedule.ui.theme.MySpbstuScheduleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private var startDestinationState by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        var keepOnScreen = true

        splashScreen.setKeepOnScreenCondition { keepOnScreen }

        lifecycleScope.launch {
            val lastSelection = viewModel.getLastSelection()
            val startDestination = if(lastSelection != null){
                Routes.Schedule.getRoute(lastSelection.mode, lastSelection.id)
            } else {
                Routes.Selection.route
            }
            startDestinationState = startDestination
            keepOnScreen = false
        }



        enableEdgeToEdge()
        setContent {
            MySpbstuScheduleTheme {
                val startDestination = startDestinationState
                if(startDestination != null){
                    MainScreen(startDestination)
                }
            }
        }
    }
}

