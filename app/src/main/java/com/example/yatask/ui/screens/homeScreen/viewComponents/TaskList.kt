package com.example.yatask.ui.screens.homeScreen.viewComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.yatask.R
import com.example.yatask.ui.models.TodoItem
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


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
