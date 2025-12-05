package com.example.myspbstuschedule.presentation.screens.schedule

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myspbstuschedule.ui.theme.MySpbstuScheduleTheme
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.max
import kotlin.math.min


private const val INITIAL_PAGE = Int.MAX_VALUE / 2
private const val PAGE_COUNT = Int.MAX_VALUE

private val days = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")

@Composable
fun ScheduleContent(
    modifier: Modifier = Modifier,
    dates: List<LocalDate>,
    selectedDay: Int,
    onDateChange: (offset: Int) -> Unit,
    onDayOfWeekClick: (dayOfWeek: Int) -> Unit
) {
    val horizontalPagerState = rememberPagerState(
        initialPage = INITIAL_PAGE,
        pageCount = { PAGE_COUNT }
    )

    LaunchedEffect(horizontalPagerState) {
        snapshotFlow {
            horizontalPagerState.currentPage
        }.collect { page ->
            val offset = page - INITIAL_PAGE
            onDateChange(offset)
            Log.d("DateTest", "offset in launched effect: $offset, current: ${page}")
        }
    }


    Column(
        modifier = modifier
    ) {
        ElevatedCard(

            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onBackground
            ),
            shape = RoundedCornerShape(bottomStart = 18.dp, bottomEnd = 18.dp),
        ) {

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                DateAndArrows(
                    pagerState = horizontalPagerState,
                    dates = dates
                )

                Spacer(modifier = Modifier.padding(8.dp))

                DaysOfMonth()

                Spacer(modifier = Modifier.padding(8.dp))

                WeekScroller(
                    pagerState = horizontalPagerState,
                    dates = dates,
                    selectedDay = selectedDay,
                    onDayOfWeekClick = onDayOfWeekClick
                )
            }

        }
    }
}

@Composable
private fun DateAndArrows(
    pagerState: PagerState,
    dates: List<LocalDate>
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
                text = formatMonth(dates[0]),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = dates[0].year.toString(),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        IconButton(
            modifier = Modifier.size(48.dp),
            onClick = {
                val nextPage = min(pagerState.currentPage + 1, pagerState.pageCount - 1)
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

private fun formatMonth(date : LocalDate) : String{
    val formatter = DateTimeFormatter.ofPattern("LLLL", Locale("ru"))
    val formattedDate = date.format(formatter).replaceFirstChar { it.uppercase() }
    return formattedDate
}

@Composable
fun DaysOfMonth() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
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
    pagerState: PagerState,
    dates: List<LocalDate>,
    selectedDay: Int,
    onDayOfWeekClick: (dayOfWeek: Int) -> Unit
) {
    HorizontalPager(
        state = pagerState,
    ) { page ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            dates.forEach {
                val dayOfWeek = it.dayOfWeek.value
                val isSelected = selectedDay == dayOfWeek

                val selectedDayColor by animateColorAsState(
                    targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                    animationSpec = tween(durationMillis = 400)
                )

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(selectedDayColor)
                        .clickable {
                            onDayOfWeekClick(dayOfWeek)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.dayOfMonth.toString(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
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
        ScheduleContent(
            dates = listOf(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(3),
                LocalDate.now().plusDays(4),
                LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(6),
            ),
            onDateChange = {},
            onDayOfWeekClick = {},
            selectedDay = 3
        )
    }
}