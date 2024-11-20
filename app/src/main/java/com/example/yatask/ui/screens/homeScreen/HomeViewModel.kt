package com.example.yatask.ui.screens.homeScreen

import com.example.yatask.ui.models.TodoItem
import kotlinx.coroutines.flow.StateFlow

interface HomeViewModel {

    val isHideCompletedItems : Boolean

    val uiState : StateFlow<HomeScreenUiState>

    fun handleEvent(event : HomeScreenUiEvent)

    fun getList()

    fun changeTaskVisibility(isHideCompletedTasks : Boolean)

    fun doneTask(note : TodoItem)

    fun removeTask(note : TodoItem)
}