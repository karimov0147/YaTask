package com.example.yatask.ui.screens.noteInfoScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yatask.ui.models.TodoItem
import com.example.yatask.domain.TodoItemsRepository
import com.example.yatask.utils.Importance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
    private val todoItemsRepository: TodoItemsRepository
) : NoteInfoViewModel, ViewModel() {
    private val _uiState = MutableStateFlow<NoteInfoScreenUiState>(NoteInfoScreenUiState.EmptyState)
    override val uiState: StateFlow<NoteInfoScreenUiState> = _uiState
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
            is NoteInfoScreenUiEvent.OnSavedButtonClicked -> saveNote(event.id)
        }
    }

    override fun getNoteById(id: String?) {
        if (id != null && id != "null") {
            scope.launch {
                withContext(Dispatchers.IO) {
                    val note = todoItemsRepository.findNote(id)
                    if (note != null) {
                        _uiState.update {
                            NoteInfoScreenUiState.Content(
                                note
                            )
                        }
                    }

                }
            }
        }
        else {
                _uiState.update {
                    NoteInfoScreenUiState.Content(
                        TodoItem(
                            id = UUID.randomUUID().toString(),
                            text = "",
                            importance = Importance.NORMAL,
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

    override fun saveNote(id: String) {
        scope.launch {
            withContext(Dispatchers.IO) {
                val note = _uiState.value as NoteInfoScreenUiState.Content
                val todoItem = note.data.copy(id = id)
                todoItemsRepository.saveNote(todoItem)
            }
        }
    }

    override fun removeNote() {
        val note =  _uiState.value as NoteInfoScreenUiState.Content
        todoItemsRepository.removeNote(note.data)
    }

    override fun showError(error: Throwable) {
        Log.d("yaTask", "showError: ${error.message} ")
    }

}