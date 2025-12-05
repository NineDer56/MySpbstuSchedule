package com.example.myspbstuschedule

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myspbstuschedule.presentation.screens.mainScreen.MainScreen
import com.example.myspbstuschedule.presentation.screens.mainScreen.MainViewModel
import com.example.myspbstuschedule.ui.theme.MySpbstuScheduleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.startDestinationState.value == null
        }
        splashScreen.setOnExitAnimationListener{ screen ->
            val zoomX = ObjectAnimator.ofFloat(
                screen.iconView,
                View.SCALE_X,
                1f,
                0.0f
            )
            zoomX.interpolator = AccelerateDecelerateInterpolator()
            zoomX.duration = 350L
            zoomX.doOnEnd { screen.remove() }

            val zoomY = ObjectAnimator.ofFloat(
                screen.iconView,
                View.SCALE_Y,
                1f,
                0.0f
            )
            zoomY.interpolator = AccelerateDecelerateInterpolator()
            zoomY.duration = 350L
            zoomY.doOnEnd { screen.remove() }

            zoomX.start()
            zoomY.start()

        }

        enableEdgeToEdge()
        setContent {
            MySpbstuScheduleTheme {
                val startDestination = viewModel.startDestinationState
                    .collectAsStateWithLifecycle().value
                if(startDestination != null){
                    MainScreen(startDestination)
                }
            }
        }
    }
}

