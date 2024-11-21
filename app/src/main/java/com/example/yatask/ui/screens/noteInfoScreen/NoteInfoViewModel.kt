package com.example.yatask.ui.screens.noteInfoScreen

import com.example.yatask.utils.Importance
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

interface NoteInfoViewModel {

    val uiState : StateFlow<NoteInfoScreenUiState>

    fun handleEvent(event: NoteInfoScreenUiEvent)

    fun getNoteById(id : String?)

    fun setText(text : String)

    fun setImportance(importance: Importance)

    fun setDate(date: Date)

    fun saveNote(id :String)

    fun removeNote()

    fun showError(error: Throwable)

}