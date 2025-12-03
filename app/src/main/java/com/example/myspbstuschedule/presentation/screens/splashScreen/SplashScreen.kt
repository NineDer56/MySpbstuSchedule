package com.example.myspbstuschedule.presentation.screens.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myspbstuschedule.R
import com.example.myspbstuschedule.ui.theme.MySpbstuScheduleTheme

@Composable
fun SplashScreen() {


    SplashScreenContent()
}

@Composable
fun SplashScreenContent(){
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.spbstu_logo),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    MySpbstuScheduleTheme(
        darkTheme = true
    ) {
        SplashScreen()
    }
}
