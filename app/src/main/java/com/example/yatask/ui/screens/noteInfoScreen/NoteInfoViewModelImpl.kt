package com.example.yatask.ui.screens.noteInfoScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yatask.ui.models.TodoItem
import com.example.yatask.domain.useCase.AddTodoUseCase
import com.example.yatask.domain.useCase.EditTodoUseCase
import com.example.yatask.domain.useCase.GetTodoUseCase
import com.example.yatask.domain.useCase.RemoveTodoUseCase
import com.example.yatask.utils.Importance
import com.example.yatask.utils.toDomainDTO
import com.example.yatask.utils.toRequestDTO
import com.example.yatask.utils.toTodoItem
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
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NoteInfoViewModelImpl @Inject constructor(
    private val getTodoUseCase : GetTodoUseCase,
    private val editTodoUseCase: EditTodoUseCase,
    private val removeTodoUseCase: RemoveTodoUseCase,
    private val addTodoUseCase : AddTodoUseCase
) : NoteInfoViewModel, ViewModel() {
    private val _uiState = MutableStateFlow<NoteInfoScreenUiState>(NoteInfoScreenUiState.EmptyState)
    override val uiState: StateFlow<NoteInfoScreenUiState> = _uiState
    private var isEditableNote = false
    private val scope = viewModelScope + CoroutineExceptionHandler { _, throwable ->
        showError(throwable)
    }

    override fun handleEvent(event: NoteInfoScreenUiEvent) {
        when (event) {
            is NoteInfoScreenUiEvent.OnNoteChanged -> setText(event.text)
            is NoteInfoScreenUiEvent.OnDeleteItem -> removeNote()
            is NoteInfoScreenUiEvent.OnDateChanged -> setDate(event.date)
            is NoteInfoScreenUiEvent.OnImportanceChanged -> setImportance(event.importance)
            is NoteInfoScreenUiEvent.OnCloseButtonClicked -> Unit
            is NoteInfoScreenUiEvent.OnSavedButtonClicked -> saveNote()
        }
    }

    override fun getNoteById(id: String?) {
        _uiState.update {
            NoteInfoScreenUiState.Loading
        }
        if (id != null && id != "null") {
            isEditableNote = true
            scope.launch {
                withContext(Dispatchers.IO) {
                    getTodoUseCase.getNoteById(id).fold(
                        onSuccess = { todoItemDTO ->
                            if (todoItemDTO != null)
                                _uiState.update {
                                    NoteInfoScreenUiState.Content(
                                        todoItemDTO.toTodoItem()
                                    )
                                }
                        },
                        onFailure = { error ->
                            _uiState.update {
                                NoteInfoScreenUiState.Error(
                                    error.message.toString()
                                )
                            }
                        }
                    )
                }
            }
        } else {
            isEditableNote = false
            _uiState.update {
                NoteInfoScreenUiState.Content(
                    TodoItem(
                        id = UUID.randomUUID().toString(),
                        text = "",
                        importance = Importance.BASIC,
                        deadline = null,
                        isCompleted = false,
                        createdAt = Date(),
                        modifiedAt = Date(),
                    )

                )
            }
        }
    }

    override fun setText(text: String) {
        _uiState.update {
            val data = (it as NoteInfoScreenUiState.Content).data.copy(text = text)
            NoteInfoScreenUiState.Content(data)
        }
    }

    override fun setImportance(importance: Importance) {
        _uiState.update {
            val data = (it as NoteInfoScreenUiState.Content).data.copy(importance = importance)
            NoteInfoScreenUiState.Content(data)
        }
    }

    override fun setDate(date: Date) {
        _uiState.update {
            val data = (it as NoteInfoScreenUiState.Content).data.copy(deadline = date)
            NoteInfoScreenUiState.Content(data)
        }
    }

    override fun saveNote() {
        val note = _uiState.value as NoteInfoScreenUiState.Content
        val todoItem = note.data
        if (isEditableNote){
            editNote(todoItem)
        }else{
            addNewNote(todoItem)
        }
    }

    private fun addNewNote(todoItem: TodoItem) {
        scope.launch {
            withContext(Dispatchers.IO) {
                addTodoUseCase.addNote(todoItem.toDomainDTO()).fold(
                    onSuccess = {},
                    onFailure = { error ->
                        _uiState.update {
                            NoteInfoScreenUiState.Error(
                                error.message.toString()
                            )
                        }
                    }
                )
            }
        }
    }

    private fun editNote(todoItem: TodoItem) {
        scope.launch {
            withContext(Dispatchers.IO) {
                editTodoUseCase.editNote(todoItem.toDomainDTO()).fold(
                    onSuccess = {},
                    onFailure = { error ->
                        _uiState.update {
                            NoteInfoScreenUiState.Error(
                                error.message.toString()
                            )
                        }
                    }
                )
            }
        }
    }

    override fun removeNote() {
        val note = _uiState.value as NoteInfoScreenUiState.Content
        scope.launch {
            withContext(Dispatchers.IO) {
                removeTodoUseCase.removeNote(note.data.id).fold(
                    onSuccess = {},
                    onFailure = { error ->
                        _uiState.update {
                            NoteInfoScreenUiState.Error(
                                error.message.toString()
                            )
                        }
                    }
                )
            }
        }
    }

    override fun showError(error: Throwable) {
        _uiState.update {
            NoteInfoScreenUiState.Error(
                error.message.toString()
            )
        }
    }

}