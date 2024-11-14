package com.example.yatask.ui.screens.noteInfoScreen

import android.util.Log
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yatask.data.models.TodoItem
import com.example.yatask.data.repository.TodoItemsRepository
import com.example.yatask.data.repository.impl.TodoItemRepositoryImpl
import com.example.yatask.utils.Importance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NoteInfoViewModelImpl @Inject constructor(
    private val todoItemsRepository : TodoItemsRepository
): NoteInfoViewModel , ViewModel() {
    private val _uiState = MutableStateFlow<NoteInfoScreenUiState>(NoteInfoScreenUiState.CreateNewTodoItem())
    override val uiState: StateFlow<NoteInfoScreenUiState> = _uiState
    private val scope = viewModelScope + CoroutineExceptionHandler{ _ , throwable ->
        showError(throwable)
    }

    init {
        getNoteById(null)
    }

    override fun handleEvent(event: NoteInfoScreenUiEvent) {
        when (event){
            is NoteInfoScreenUiEvent.OnNoteChanged -> setText(event.text)
            is NoteInfoScreenUiEvent.OnDeleteItem -> removeNote()
            is NoteInfoScreenUiEvent.OnDateChanged -> setDate(event.date)
            is NoteInfoScreenUiEvent.OnImportanceChanged -> setImportance(event.importance)
            is NoteInfoScreenUiEvent.OnCloseButtonClicked -> Unit
            is NoteInfoScreenUiEvent.OnSavedButtonClicked -> saveNote()
        }
    }

    override fun getNoteById(id: String?) {
        if (id != null){
            scope.launch {
                withContext(Dispatchers.IO){
                    val note =  todoItemsRepository.findNote(id)
                    if (note != null){
                        _uiState.update {
                            NoteInfoScreenUiState.EditTodoItem(
                                note.id ,
                                note.text,
                                note.importance ,
                                note.deadline ,
                                note.modifiedAt
                            )
                        }
                    }

                }
            }
        }
    }

    override fun setText(text: String) {
        _uiState.update {
            (it as NoteInfoScreenUiState.CreateNewTodoItem).copy(text = text)
        }
    }

    override fun setImportance(importance: Importance) {
        _uiState.update {
            (it as NoteInfoScreenUiState.CreateNewTodoItem).copy(importance = importance)
        }
    }

    override fun setDate(date: Date) {
        _uiState.update {
            (it as NoteInfoScreenUiState.CreateNewTodoItem).copy(deadline = date)
        }
    }

    override fun saveNote() {
        scope.launch {
            withContext(Dispatchers.IO){
                val note = _uiState.value as NoteInfoScreenUiState.CreateNewTodoItem
                val todoItem = TodoItem(
                    id = note.id ,
                    text = note.text ,
                    importance = note.importance ,
                    deadline = note.deadline,
                    isCompleted = false ,
                    createdAt = note.createdAt ,
                    modifiedAt = note.createdAt
                )
                todoItemsRepository.addNote(todoItem)
            }
        }
    }

    override fun removeNote() {
//        todoItemsRepository.removeNote()
    }

    override fun showError(error: Throwable) {

    }

}