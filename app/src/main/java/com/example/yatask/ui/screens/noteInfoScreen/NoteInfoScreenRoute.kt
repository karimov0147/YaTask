package com.example.yatask.ui.screens.noteInfoScreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NoteInfoScreenRoute(
    viewModel: NoteInfoViewModel = hiltViewModel<NoteInfoViewModelImpl>(),
    navArgs : String?,
    onBackClicked : () -> Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.getNoteById(navArgs)

    NoteScreen(
        uiState = uiState ,
        onTextChanged = {viewModel.handleEvent(NoteInfoScreenUiEvent.OnNoteChanged(it))},
        onDateSelected = {viewModel.handleEvent(NoteInfoScreenUiEvent.OnDateChanged(it))},
        onImportanceChanged = {viewModel.handleEvent(NoteInfoScreenUiEvent.OnImportanceChanged(it))},
        onSaveClicked = {viewModel.handleEvent(NoteInfoScreenUiEvent.OnSavedButtonClicked) ; onBackClicked.invoke()},
        onBackClicked = {viewModel.handleEvent(NoteInfoScreenUiEvent.OnCloseButtonClicked) ; onBackClicked.invoke()},
        onRemoveClicked = {viewModel.handleEvent(NoteInfoScreenUiEvent.OnDeleteItem) ; onBackClicked.invoke()},
    )


}