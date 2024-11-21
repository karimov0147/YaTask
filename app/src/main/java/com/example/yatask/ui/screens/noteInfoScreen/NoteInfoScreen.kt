package com.example.yatask.ui.screens.noteInfoScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.yatask.utils.Importance
import java.util.Date

@Composable
fun NoteScreen(
    uiState: NoteInfoScreenUiState,
    onTextChanged : (String) -> Unit,
    onDateSelected : (Date) -> Unit,
    onImportanceChanged: (Importance) -> Unit,
    onSaveClicked : (id: String) -> Unit,
    onBackClicked : () -> Unit,
    onRemoveClicked : () -> Unit,
){
    Box(modifier = Modifier.fillMaxSize()){
        when(uiState){
            is NoteInfoScreenUiState.Content -> NoteInfoScreenContent(
                id = uiState.data.id,
                text = uiState.data.text ,
                importance = uiState.data.importance ,
                date = uiState.data.deadline,
                onTextChanged = onTextChanged ,
                onDateSelected = onDateSelected ,
                onSaveClicked = onSaveClicked ,
                onBackClicked = onBackClicked ,
                onRemoveClicked = onRemoveClicked,
                onImportanceChanged = onImportanceChanged
            )

            is NoteInfoScreenUiState.EmptyState -> NoteInfoScreenContent(
                onTextChanged = onTextChanged ,
                onDateSelected = onDateSelected ,
                onSaveClicked = onSaveClicked ,
                onBackClicked = onBackClicked ,
                onRemoveClicked = onRemoveClicked,
                onImportanceChanged = onImportanceChanged
            )
        }
    }
}








