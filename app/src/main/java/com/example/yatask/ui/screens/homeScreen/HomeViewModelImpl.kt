package com.example.yatask.ui.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yatask.ui.models.TodoItem
import com.example.yatask.domain.useCase.EditTodoUseCase
import com.example.yatask.domain.useCase.GetTodoUseCase
import com.example.yatask.domain.useCase.RemoveTodoUseCase
import com.example.yatask.utils.toDomainDTO
import com.example.yatask.utils.toRequestDTO
import com.example.yatask.utils.toTodoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val getTodoUseCase : GetTodoUseCase,
    private val editTodoUseCase: EditTodoUseCase,
    private val removeTodoUseCase: RemoveTodoUseCase
) : HomeViewModel , ViewModel() {
    override var isHideCompletedItems = false
    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Content(listOf() , isHideCompletedItems , 0))
    override val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()
    private val scope = viewModelScope + CoroutineExceptionHandler{ _ , throwable ->
        _uiState.update {
            HomeScreenUiState.Error(
                throwable.message.toString()
            )
        }
    }

    override fun handleEvent(event: HomeScreenUiEvent) {
        when(event){
            is HomeScreenUiEvent.OnDeleteItem -> removeTask(event.todoItem)
            is HomeScreenUiEvent.OnItemCompleted -> doneTask(event.todoItem)
            is HomeScreenUiEvent.OnCompletedItemsHide -> changeTaskVisibility(event.hideCompletedItems)
        }
    }

    override fun getList() {
        _uiState.update {
            HomeScreenUiState.Loading
        }
        scope.launch {
            withContext(Dispatchers.IO){
                getTodoUseCase.getAllNoteList().fold(
                    onSuccess = { list ->
                        _uiState.update {
                           HomeScreenUiState.Content(
                                list.map { it.toTodoItem() } ,
                               isHideCompletedItems,
                               list.filter { it.isCompleted }.size
                           )
                        }
                    } ,
                    onFailure = { error ->
                        _uiState.update {
                            HomeScreenUiState.Error(
                                error.message.toString()
                            )
                        }
                    }
                )
            }
        }
    }

    override fun changeTaskVisibility(isHideCompletedTasks: Boolean) {
        isHideCompletedItems = isHideCompletedTasks
        getList()
    }

    override fun doneTask(note: TodoItem) {
        _uiState.update {
            HomeScreenUiState.Loading
        }
        scope.launch(Dispatchers.IO) {
           editTodoUseCase
               .editNote(note.copy(isCompleted = true)
               .toDomainDTO())
               .fold(
               onSuccess = {
                   getList()
               },
               onFailure = { error ->
                   _uiState.update {
                       HomeScreenUiState.Error(
                           error.message.toString()
                       )
                   }
               }
           )

       }
    }

    override fun removeTask(note: TodoItem) {
        _uiState.update {
            HomeScreenUiState.Loading
        }
        scope.launch {
            withContext(Dispatchers.IO){
                removeTodoUseCase.removeNote(note.id)
                    .fold(
                        onSuccess = {
                            getList()
                        },
                        onFailure = { error ->
                            _uiState.update {
                                HomeScreenUiState.Error(
                                    error.message.toString()
                                )
                            }
                        }
                    )

            }

        }
    }

}