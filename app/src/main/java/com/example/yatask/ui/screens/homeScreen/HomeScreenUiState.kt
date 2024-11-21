package com.example.yatask.ui.screens.homeScreen

import android.os.Message
import com.example.yatask.ui.models.TodoItem

sealed interface HomeScreenUiState {

    data class Content(val todoList : List<TodoItem>,
                       val isHideCompletedState : Boolean,
                       val completedTaskSize : Int) : HomeScreenUiState

    data class Error(val message: String) : HomeScreenUiState

    data object Loading : HomeScreenUiState

}
