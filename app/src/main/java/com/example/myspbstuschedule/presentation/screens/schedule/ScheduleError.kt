package com.example.myspbstuschedule.presentation.screens.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScheduleError(
    modifier: Modifier = Modifier,
    message: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Ошибка: $message",
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.padding(8.dp))

        // TODO(кнопка перезагрузки)
    }
}