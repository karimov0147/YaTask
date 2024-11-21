package com.example.yatask.ui.screens.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yatask.ui.models.TodoItem
import com.example.yatask.domain.TodoItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val todoItemsRepository: TodoItemsRepository
) : HomeViewModel , ViewModel() {
    override var isHideCompletedItems = false
    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Content(listOf() , isHideCompletedItems , 0))
    override val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()
    private val scope = viewModelScope + CoroutineExceptionHandler{ _ , throwable ->
        Log.d("yaTask", ": $throwable")
    }

    override fun handleEvent(event: HomeScreenUiEvent) {
        when(event){
            is HomeScreenUiEvent.OnDeleteItem -> removeTask(event.todoItem)
            is HomeScreenUiEvent.OnItemCompleted -> doneTask(event.todoItem)
            is HomeScreenUiEvent.OnCompletedItemsHide -> changeTaskVisibility(event.hideCompletedItems)
        }
    }

    override fun getList() {
        scope.launch {
            withContext(Dispatchers.IO){
                val list = todoItemsRepository.getAllNoteList().value
                val size = todoItemsRepository.complectedTaskSize().first()
                _uiState.update {
                    HomeScreenUiState.Content(
                        list,
                        isHideCompletedItems ,
                        size
                    )
                }
            }
        }
    }

    override fun changeTaskVisibility(isHideCompletedTasks: Boolean) {
        isHideCompletedItems = isHideCompletedTasks
        getList()
    }

    override fun doneTask(note: TodoItem) {
        scope.launch(Dispatchers.IO) {
           todoItemsRepository.editNote(note.copy(isCompleted = true))
           getList()
       }
    }

    override fun removeTask(note: TodoItem) {
        scope.launch {
            withContext(Dispatchers.IO){
                todoItemsRepository.removeNote(note)
                getList()
            }

        }
    }

}