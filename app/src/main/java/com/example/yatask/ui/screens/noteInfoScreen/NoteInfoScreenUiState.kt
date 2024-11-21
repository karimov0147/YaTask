package com.example.yatask.ui.screens.noteInfoScreen

import com.example.yatask.ui.models.TodoItem

sealed interface NoteInfoScreenUiState {

    data class Content(
        val data : TodoItem
    ) : NoteInfoScreenUiState

    data object EmptyState : NoteInfoScreenUiState

}