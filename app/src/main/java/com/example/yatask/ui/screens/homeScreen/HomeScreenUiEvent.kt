package com.example.yatask.ui.screens.homeScreen

import com.example.yatask.data.models.TodoItem

sealed interface HomeScreenUiEvent {
    data class OnDeleteItem(val todoItem: TodoItem) : HomeScreenUiEvent
    data class OnItemCompleted(val todoItem: TodoItem) : HomeScreenUiEvent
    data class OnCompletedItemsHide(val hideCompletedItems : Boolean) : HomeScreenUiEvent
}