package com.example.myspbstuschedule.presentation.screens.selection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myspbstuschedule.domain.model.Faculty
import com.example.myspbstuschedule.domain.model.Group
import com.example.myspbstuschedule.ui.theme.MySpbstuScheduleTheme

@Composable
fun GroupItem(
    group :  Group,
    onGroupItemClick : (groupId : Int, name : String) -> Unit
){
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onGroupItemClick(group.id, group.name) }
    ) {
        Text(
            text = group.name,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp
        )
        Text(
            text = group.faculty.name,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 16.sp,
            lineHeight = 20.sp
        )
    }
}




@Composable
@Preview
fun GroupItemPreview(){
    MySpbstuScheduleTheme(
        darkTheme = true
    ) {
        GroupItem(
            Group(
                1,
                "5130903/30302",
                2,
                Faculty(
                    1,
                    "Интитут компьютерных наук и кибербезопасности",
                    "ИКНК"
                )
            ),
            {a, b ->},
        )
    }
}