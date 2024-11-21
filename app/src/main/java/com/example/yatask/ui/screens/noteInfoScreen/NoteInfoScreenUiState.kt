package com.example.yatask.ui.screens.noteInfoScreen

import com.example.yatask.ui.models.TodoItem
import com.example.yatask.ui.screens.homeScreen.HomeScreenUiState

sealed interface NoteInfoScreenUiState {

    data class Content(
        val data : TodoItem
    ) : NoteInfoScreenUiState

    data object EmptyState : NoteInfoScreenUiState

    data class Error(val message: String) : NoteInfoScreenUiState

    data object Loading : NoteInfoScreenUiState

}