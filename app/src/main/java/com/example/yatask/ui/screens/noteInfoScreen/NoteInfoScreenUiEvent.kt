package com.example.yatask.ui.screens.noteInfoScreen

import com.example.yatask.data.models.TodoItem
import com.example.yatask.utils.Importance
import java.util.Date

sealed interface NoteInfoScreenUiEvent {
    data object OnCloseButtonClicked : NoteInfoScreenUiEvent
    data object OnSavedButtonClicked : NoteInfoScreenUiEvent
    data class OnNoteChanged(val text : String) : NoteInfoScreenUiEvent
    data class OnImportanceChanged(val importance: Importance) : NoteInfoScreenUiEvent
    data class OnDateChanged(val date: Date) : NoteInfoScreenUiEvent
    data object OnDeleteItem : NoteInfoScreenUiEvent
}