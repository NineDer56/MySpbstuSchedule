package com.example.myspbstuschedule.presentation.screens.schedule

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myspbstuschedule.ui.theme.MySpbstuScheduleTheme
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.math.max
import kotlin.math.min


private const val INITIAL_PAGE = Int.MAX_VALUE / 2
private const val PAGE_COUNT = Int.MAX_VALUE

@Composable
fun ScheduleContent(
    modifier: Modifier = Modifier,
    dates: List<LocalDate>,
    onDateChange: (offset: Int) -> Unit
) {
    val horizontalPagerState = rememberPagerState(
        initialPage = INITIAL_PAGE,
        pageCount = { PAGE_COUNT }
    )

    LaunchedEffect(horizontalPagerState) {
        val offset = horizontalPagerState.currentPage - INITIAL_PAGE
        onDateChange(offset)
    }

    Column(
        modifier = modifier
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
                    pagerState = horizontalPagerState,
                    dates = dates,
                    onDateChange = onDateChange
                )

                Spacer(modifier = Modifier.padding(8.dp))

                DaysOfMonth()

                Spacer(modifier = Modifier.padding(8.dp))

                WeekScroller(
                    pagerState = horizontalPagerState,
                    dates = dates
                )
            }

        }
    }
}

@Composable
private fun DateAndArrows(
    pagerState: PagerState,
    dates: List<LocalDate>,
    onDateChange: (offset: Int) -> Unit
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
                val offset = prevPage - INITIAL_PAGE
                onDateChange(offset)
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
                text = dates[0].month.name.lowercase(),
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
                val nextPage = min(pagerState.currentPage + 1, pagerState.pageCount)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(nextPage)
                }
                val offset = nextPage - INITIAL_PAGE
                onDateChange(offset)
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
    pagerState: PagerState,
    dates: List<LocalDate>
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
//            val dates = get7DaysOfWeek(page - INITIAL_PAGE)

            dates.forEach {
                Text(
                    text = it.dayOfMonth.toString(),
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
            onDateChange = {}
        )
    }
}