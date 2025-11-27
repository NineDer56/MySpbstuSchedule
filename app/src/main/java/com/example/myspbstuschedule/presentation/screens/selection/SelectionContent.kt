package com.example.myspbstuschedule.presentation.screens.selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.delete
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myspbstuschedule.ui.theme.MySpbstuScheduleTheme

@Composable
fun SelectionContent(
    modifier: Modifier = Modifier,
    onSearchClick: (name: String, mode: SearchMode) -> Unit,
) {

    var selectionState by remember { mutableIntStateOf(SearchMode.GROUP.value) }
    val titles = listOf("Группы", "Преподаватели")

    val textFieldState = rememberTextFieldState()

    LaunchedEffect(selectionState) {
        textFieldState.edit { delete(0, textFieldState.text.length) }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        PrimaryTabRow(selectedTabIndex = selectionState) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = index == selectionState,
                    text = {
                        Text(
                            text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    onClick = {
                        selectionState = index
                    }
                )
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            SearchInput(
                label = if (selectionState == SearchMode.GROUP.value) "Номер" else "Фио",
                state = textFieldState,
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    val name = textFieldState.text.toString().trim()
                    if (name.isEmpty()) return@Button

                    if (selectionState == SearchMode.GROUP.value) {
                        onSearchClick(name, SearchMode.GROUP)
                    } else {
                        onSearchClick(name, SearchMode.TEACHER)
                    }
                },
            ) {
                Text(
                    text = "Поиск",
                    maxLines = 1,
                )
            }
        }
    }
}


@Composable
private fun SearchInput(
    label: String,
    state: TextFieldState,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        state = state,
        lineLimits = TextFieldLineLimits.SingleLine,
        label = { Text(label) },

        )
}


@Preview
@Composable
fun SelectionContentPreview() {
    MySpbstuScheduleTheme(
        darkTheme = true
    ) {
        SelectionContent(
            modifier = Modifier,
            onSearchClick = { name, mode -> }
        )
    }
}