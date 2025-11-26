package com.example.myspbstuschedule.presentation.screens.schedule

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myspbstuschedule.domain.model.Auditory
import com.example.myspbstuschedule.domain.model.Building
import com.example.myspbstuschedule.domain.model.Lesson
import com.example.myspbstuschedule.domain.model.LessonType
import com.example.myspbstuschedule.domain.model.Teacher
import com.example.myspbstuschedule.ui.theme.MySpbstuScheduleTheme
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

@Composable
fun ScheduleContent(
    modifier: Modifier = Modifier
) {
    val horizontalPagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onBackground
            ),
            shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
        ) {

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                DateAndArrows(
                    pagerState = horizontalPagerState
                )

                Spacer(modifier = Modifier.padding(8.dp))

                DaysOfMonth()

                Spacer(modifier = Modifier.padding(8.dp))

                WeekScroller(
                    pagerState = horizontalPagerState
                )
            }

        }

        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            repeat(4) {
                item() {
                    ScheduleItem(
                        modifier = Modifier.padding(8.dp),
                        lesson = Lesson(
                            subject = "Администрирование информационных систем",
                            timeStart = "12:00",
                            timeEnd = "13:40",
                            lessonType = LessonType(1, "Лабораторные", "Лаб"),
                            groups = emptyList(),
                            teachers = listOf(Teacher(1, "Брык Иван Юрьевич", "")),
                            auditories = listOf(
                                Auditory(
                                    1,
                                    "215",
                                    Building(1, "11-й учебный корпус", "11 к.")
                                )
                            )
                        )
                    )
                }
            }
        }

    }
}

@Composable
private fun DateAndArrows(
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            modifier = Modifier.size(48.dp),
            onClick = {
                val prevPage = max(pagerState.currentPage - 1, 0)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(prevPage)
                }
            }
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "November",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "2025",
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        IconButton(
            modifier = Modifier.size(48.dp),
            onClick = {
                val nextPage = min(pagerState.currentPage + 1, pagerState.pageCount)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(nextPage)
                }
            },
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun DaysOfMonth() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val days = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")

        days.forEach {
            Text(
                text = it,
                modifier = Modifier.size(28.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun WeekScroller(
    pagerState: PagerState
) {

    LaunchedEffect(pagerState) {
        snapshotFlow {
            pagerState.currentPage
        }.collect { page ->
            Log.d("Test", page.toString())
        }
    }

    HorizontalPager(
        state = pagerState,
    ) { page ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val dates = listOf("1", "2", "3", "4", "5", "6", "7")

            dates.forEach {
                Text(
                    text = it,
                    modifier = Modifier.size(28.dp),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}


@Preview
@Composable
fun ScheduleContentPreview() {
    MySpbstuScheduleTheme(
        darkTheme = true
    ) {
        ScheduleContent()
    }
}