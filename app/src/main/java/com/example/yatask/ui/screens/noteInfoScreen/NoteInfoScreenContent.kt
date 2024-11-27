package com.example.yatask.ui.screens.noteInfoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.yatask.ui.screens.noteInfoScreen.viewComponents.AddNoteLayout
import com.example.yatask.ui.screens.noteInfoScreen.viewComponents.CustomAppBar
import com.example.yatask.ui.screens.noteInfoScreen.viewComponents.DateLayout
import com.example.yatask.ui.screens.noteInfoScreen.viewComponents.DatePickerModal
import com.example.yatask.ui.screens.noteInfoScreen.viewComponents.DeleteLayout
import com.example.yatask.ui.screens.noteInfoScreen.viewComponents.ImportanceLayout
import com.example.yatask.utils.Importance
import java.util.Date

@Composable
fun NoteInfoScreenContent(
    id : String = "",
    text : String = "",
    importance : Importance = Importance.BASIC,
    date: Date? = null ,
    onImportanceChanged: (Importance) -> Unit,
    onTextChanged : (String) -> Unit,
    onDateSelected : (Date) -> Unit,
    onSaveClicked : (id : String) -> Unit,
    onBackClicked : () -> Unit,
    onRemoveClicked : () -> Unit,
){
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value){
        DatePickerModal(onDateSelected = {
            onDateSelected.invoke(Date(it ?: return@DatePickerModal))
        } , onDismiss = {
            showDialog.value = false
        })
    }
    Column(modifier = Modifier.fillMaxSize()) {
        CustomAppBar(saveClicked = onSaveClicked , onBackClicked = onBackClicked , id)
        Column (modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF7F6F2)) ,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top) {
            AddNoteLayout(text , onTextChanged)
            ImportanceLayout(importance , onImportanceChanged)
            DateLayout(showDialog  , date)
            DeleteLayout(onRemoveClicked , onBackClicked )
        }
    }
}

