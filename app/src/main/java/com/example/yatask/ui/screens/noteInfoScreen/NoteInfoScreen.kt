package com.example.yatask.ui.screens.noteInfoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NoOpNavigator
import com.example.yatask.R
import com.example.yatask.data.models.TodoItem
import com.example.yatask.utils.Importance
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun NoteScreen(
    uiState: NoteInfoScreenUiState,
    onTextChanged : (String) -> Unit,
    onDateSelected : (Date) -> Unit,
    onImportanceChanged: (Importance) -> Unit,
    onSaveClicked : () -> Unit,
    onBackClicked : () -> Unit,
    onRemoveClicked : () -> Unit,
){
    Box(modifier = Modifier.fillMaxSize()){
        when(uiState){
            is NoteInfoScreenUiState.CreateNewTodoItem -> NoteInfoScreenContent(
                text = uiState.text ,
                importance = uiState.importance ,
                date = uiState.deadline,
                onTextChanged = onTextChanged ,
                onDateSelected = onDateSelected ,
                onSaveClicked = onSaveClicked ,
                onBackClicked = onBackClicked ,
                onRemoveClicked = onRemoveClicked,
                onImportanceChanged = onImportanceChanged

            )
            is NoteInfoScreenUiState.EditTodoItem -> NoteInfoScreenContent(
                text = uiState.text ,
                importance = uiState.importance ,
                date = uiState.deadline,
                onTextChanged = onTextChanged ,
                onDateSelected = onDateSelected ,
                onSaveClicked = onSaveClicked ,
                onBackClicked = onBackClicked ,
                onRemoveClicked = onRemoveClicked ,
                onImportanceChanged = onImportanceChanged
            )
        }
    }
}








