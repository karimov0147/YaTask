package com.example.yatask.ui.screens.homeScreen

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
import com.example.yatask.ui.models.TodoItem
import com.example.yatask.ui.screens.homeScreen.viewComponents.TaskList
import com.example.yatask.utils.Importance
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun HomeScreenContent(
    list: List<TodoItem> = listOf(),
    onDoneItem : (TodoItem) -> Unit = {},
    onRemoveItem : (TodoItem) -> Unit = {},
    onClickItem : (String) -> Unit = {},
    innerPadding : PaddingValues ,
    isHideComplectedTasks : Boolean = false,

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
