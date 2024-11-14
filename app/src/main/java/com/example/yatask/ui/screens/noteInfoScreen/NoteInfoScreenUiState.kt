package com.example.yatask.ui.screens.noteInfoScreen

import com.example.yatask.utils.Importance
import java.util.Date
import java.util.UUID

sealed interface NoteInfoScreenUiState {

    data class CreateNewTodoItem(
        val id : String = UUID.randomUUID().toString() ,
        val text : String = "",
        val importance: Importance = Importance.NORMAL,
        val deadline : Date? = null,
        val createdAt : Date = Date()
    ) : NoteInfoScreenUiState

    data class EditTodoItem(
        val id : String  ,
        val text : String,
        val importance: Importance,
        val deadline : Date?,
        val modifiedAt : Date
    ) : NoteInfoScreenUiState
}