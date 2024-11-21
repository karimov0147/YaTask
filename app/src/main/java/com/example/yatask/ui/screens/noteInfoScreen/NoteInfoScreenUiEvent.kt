package com.example.yatask.ui.screens.noteInfoScreen

import com.example.yatask.utils.Importance
import java.util.Date

sealed interface NoteInfoScreenUiEvent {
    data object OnCloseButtonClicked : NoteInfoScreenUiEvent
    data class OnSavedButtonClicked(val id : String) : NoteInfoScreenUiEvent
    data class OnNoteChanged(val text : String) : NoteInfoScreenUiEvent
    data class OnImportanceChanged(val importance: Importance) : NoteInfoScreenUiEvent
    data class OnDateChanged(val date: Date) : NoteInfoScreenUiEvent
    data object OnDeleteItem : NoteInfoScreenUiEvent
}