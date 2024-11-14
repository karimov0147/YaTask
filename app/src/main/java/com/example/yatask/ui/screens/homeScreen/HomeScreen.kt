package com.example.yatask.ui.screens.homeScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.yatask.R
import com.example.yatask.data.models.TodoItem
import com.example.yatask.utils.Importance
import com.example.yatask.utils.NavigationPath
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeScreenUiState,
    onDoneTask : (TodoItem) -> Unit ,
    onRemoveTask : (TodoItem) -> Unit,
    onClickTask : (String) -> Unit,
    onClickFab : () -> Unit ,
    onHideCompletedClick : () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val isCollapsed = remember { derivedStateOf { scrollBehavior.state.collapsedFraction > 0.5 } }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF7F6F2),
                    scrolledContainerColor = Color(0xFFF7F6F2),
                    titleContentColor = Color.Black,),
                title = {
                    Row(
                        modifier = Modifier
                            .padding(end = 24.dp, start = 60.dp)

                    ) {
                        Column(modifier = Modifier.weight(1f)) {

                            Text("Мои дела",
                                fontSize = 32.sp,
                                fontWeight = FontWeight(500),
                                color = Color.Black)

                            if (isCollapsed.value) {
                                Text(
                                    "Выполнено — ${(uiState as HomeScreenUiState.Content).completedTaskSize}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF8E8E90)
                                )
                            }
                        }
                        Image(
                            painter =  if ((uiState as HomeScreenUiState.Content).isHideCompletedState) painterResource(R.drawable.ic_show) else painterResource(R.drawable.ic_hide)  ,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(bottom = if (isCollapsed.value) 10.dp else 4.dp)
                                .size(24.dp)
                                .align(Alignment.Bottom)
                                .clickable(onClick = {
                                    onHideCompletedClick.invoke()
                                })
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                modifier = Modifier.shadow(if (!isCollapsed.value) 0.dp else 6.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(0xFF007AFF),
                contentColor = Color.White,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(6.dp),
                modifier = Modifier.padding(bottom = 24.dp),
                onClick = {
                   onClickFab.invoke()
                },
            ) {
                Icon(painterResource(R.drawable.ic_fab), "Floating action button.")
            }
        },
    )
    { innerPadding ->
        when (uiState){
            is HomeScreenUiState.Content -> HomeScreenContent(
                list = uiState.todoList ,
                onDoneItem = onDoneTask ,
                onRemoveItem = onRemoveTask,
                onClickItem = onClickTask,
                innerPadding = innerPadding,
                isHideComplectedTasks = uiState.isHideCompletedState
            )
        }
    }
}

