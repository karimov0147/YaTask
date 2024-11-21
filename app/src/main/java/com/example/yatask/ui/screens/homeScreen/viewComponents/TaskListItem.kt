package com.example.yatask.ui.screens.homeScreen.viewComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yatask.R
import com.example.yatask.ui.models.TodoItem
import com.example.yatask.utils.Importance


@Composable
fun Item(note : TodoItem, onDoneItem: (TodoItem) -> Unit, onClickItem: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable {
                onClickItem.invoke(note.id)
            }
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp)
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked =  note.isCompleted,
            colors = CheckboxColors(
                checkedBorderColor =  Color(0xFF34C759) ,
                checkedCheckmarkColor = Color.White,
                uncheckedCheckmarkColor = Color.Transparent,
                checkedBoxColor = Color(0xFF34C759),
                uncheckedBoxColor = if (note.importance == Importance.HIGH) Color(0xFFFF3B30).copy(alpha = 0.16f) else Color.Transparent,
                disabledCheckedBoxColor = Color.Transparent,
                disabledUncheckedBoxColor = Color.Transparent,
                disabledIndeterminateBoxColor = Color.Transparent,
                uncheckedBorderColor = if (note.importance == Importance.HIGH) Color(0xFFFF3B30) else Color.Black.copy(alpha = 0.2f),
                disabledBorderColor = Color.Transparent,
                disabledUncheckedBorderColor = Color.Transparent,
                disabledIndeterminateBorderColor = Color.Transparent
            ),
            modifier = Modifier.size(24.dp),
            onCheckedChange = {
                onDoneItem.invoke(note)
            },
        )

        Text(
            if (note.importance == Importance.HIGH) "‼\uFE0F "+note.text else note.text,
            color = if (note.isCompleted) Color.Black.copy(alpha = 0.3f) else Color.Black,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            maxLines = 3,
            style = if (note.isCompleted) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle(textDecoration = TextDecoration.None),
            textAlign = TextAlign.Left,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .weight(1f)
        )

        Icon(
            modifier = Modifier
                .size(24.dp),
            painter = painterResource(R.drawable.ic_info),
            contentDescription = null,

            )
    }
}