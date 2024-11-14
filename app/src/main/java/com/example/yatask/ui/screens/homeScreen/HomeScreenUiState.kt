package com.example.yatask.ui.screens.homeScreen

import com.example.yatask.data.models.TodoItem
import kotlinx.coroutines.flow.StateFlow

sealed interface HomeScreenUiState{
    data class Content(val todoList : List<TodoItem>,
                       val isHideCompletedState :Boolean,
                       val completedTaskSize : Int) : HomeScreenUiState
}
