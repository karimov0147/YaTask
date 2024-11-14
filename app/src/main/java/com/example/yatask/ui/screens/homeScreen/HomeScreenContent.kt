package com.example.yatask.ui.screens.homeScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yatask.R
import com.example.yatask.data.models.TodoItem
import com.example.yatask.utils.Importance
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun HomeScreenContent(
    list: List<TodoItem>,
    onDoneItem : (TodoItem) -> Unit,
    onRemoveItem : (TodoItem) -> Unit,
    onClickItem : (String) -> Unit,
    innerPadding : PaddingValues,
    isHideComplectedTasks : Boolean
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(color = Color(0xFFF7F6F2))
    ){
        TaskList(
                list = list ,
                isHideCompletedTasks = isHideComplectedTasks,
                onRemoveItem = onRemoveItem ,
                onDoneItem = onDoneItem ,
                onClickItem = onClickItem)
    }
}

@Composable
fun TaskList(list: List<TodoItem>,
             isHideCompletedTasks : Boolean,
             onDoneItem : (TodoItem) -> Unit,
             onRemoveItem : (TodoItem) -> Unit,
             onClickItem : (String) -> Unit)
{
    LazyColumn(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.06f),
                        Color.Black.copy(alpha = 0.12f)
                    )
                ), shape = RoundedCornerShape(8.dp)
            )
            .padding(bottom = 2.dp, start = 1.dp, end = 1.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        items(if (isHideCompletedTasks) list.filter { it.isCompleted } else list) {
            val delete = SwipeAction(
                onSwipe = {
                    onRemoveItem.invoke(it)
                },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_basket),
                        contentDescription = null,
                        modifier = Modifier.padding(16.dp),
                        tint = Color.White
                    )
                },
                background = Color(0xFFFF3B30),
            )

            val done = SwipeAction(
                onSwipe = {
                    onDoneItem.invoke(it)
                },
                icon = {
                    Icon(
                        painterResource(id = R.drawable.ic_done),
                        contentDescription = null,
                        modifier = Modifier.padding(16.dp),
                        tint = Color.White
                    )
                },
                background = Color(0xFF34C759) ,
            )

            SwipeableActionsBox(
                modifier = Modifier,
                swipeThreshold = 40.dp,
                startActions = listOf(done),
                endActions = listOf(delete),
                backgroundUntilSwipeThreshold = Color(0xFFF7F6F2)
            ) {
                Item(it , onDoneItem,  onClickItem)
            }
        }
    }
}

@Composable
fun Item(note : TodoItem, onDoneItem: (TodoItem) -> Unit , onClickItem: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp)
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
            .clickable {
                onClickItem.invoke(note.id)
//                navigationController.navigate(
//                    NavigationPath.NOTE_SCREEN.name + "/{id}".replace(
//                        "{id}",
//                        note.id
//                    )
//                )
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        val context = LocalContext.current

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