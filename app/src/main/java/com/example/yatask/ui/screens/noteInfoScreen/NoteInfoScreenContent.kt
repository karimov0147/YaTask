package com.example.yatask.ui.screens.noteInfoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.yatask.R
import com.example.yatask.utils.Importance
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun NoteInfoScreenContent(
    text : String,
    importance : Importance,
    date: Date? ,
    onImportanceChanged: (Importance) -> Unit,
    onTextChanged : (String) -> Unit,
    onDateSelected : (Date) -> Unit,
    onSaveClicked : () -> Unit,
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
        CustomAppBar(saveClicked = onSaveClicked , onBackClicked = onBackClicked)
        Column (modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF7F6F2)) ,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top) {
            AddNoteLayout(text , onTextChanged)
            ImportanceLayout(importance , onImportanceChanged)
            DateLayout(showDialog  , date)
            DeleteLayout(onRemoveClicked , onBackClicked)
        }
    }
}


@Composable
fun CustomAppBar(saveClicked: () -> Unit , onBackClicked: () -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0xFFF7F6F2)) ,
        verticalAlignment = Alignment.CenterVertically)
    {
        Icon(painter = painterResource(R.drawable.ic_fab),
            contentDescription = null ,
            tint = Color.Black ,
            modifier = Modifier
                .size(56.dp)
                .clickable {
                    onBackClicked.invoke()
                }
                .padding(16.dp)
                .rotate(45f)

        )
        Spacer(Modifier.weight(1f))

        Text(modifier = Modifier
            .height(56.dp)
            .clickable {
                saveClicked.invoke()
                onBackClicked.invoke()
            }
            .padding(end = 16.dp, start = 16.dp)
            .wrapContentHeight(align = Alignment.CenterVertically)
            ,
            text = "Сохранить".uppercase(),
            color = Color( 0xFF007AFF) ,
            fontSize = 14.sp ,
            lineHeight = 24.sp ,)

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteLayout( text : String , onTextChanged: (String) -> Unit){
    var input by remember { mutableStateOf(text) }
    TextField(
        value = input,
        onValueChange = { newText ->
            input = newText
            onTextChanged(newText.trim())
        },
        modifier = Modifier
            .clickable { }
            .fillMaxWidth()
            .defaultMinSize(minHeight = 112.dp)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.06f),
                        Color.Black.copy(alpha = 0.12f)
                    )
                ), shape = RoundedCornerShape(8.dp)
            )
            .padding(bottom = 2.dp, start = 1.dp, end = 1.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent),
        textStyle = TextStyle(fontSize = 16.sp),
        placeholder = {
            Text("Что надо сделать...",
                fontSize = 16.sp,
                fontWeight = FontWeight(400) ,
                lineHeight = 16.sp,
                color = Color.Black.copy(alpha = 0.3f),
                modifier = Modifier
            )
        },
    )
}

@Composable
fun ImportanceLayout(importance: Importance , onImportanceChanged: (Importance) -> Unit){
    Column (modifier = Modifier
        .padding(top = 12.dp)
        .height(72.dp)
        .fillMaxWidth()
        .clickable {

        }) {

        Text("Важность" ,
            fontSize = 16.sp ,
            lineHeight = 18.sp ,
            fontWeight = FontWeight(400),
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp , top = 16.dp)
        )

//        DropDownDemo()

        Text(importance.name ,
            fontSize = 14.sp ,
            lineHeight = 16.sp ,
            fontWeight = FontWeight(400),
            color = Color.Black.copy(alpha = 0.2f),
            modifier = Modifier.padding(start = 16.dp , top = 4.dp)
        )

    }

    HorizontalDivider(
        thickness = 0.5.dp ,
        modifier = Modifier.padding(start = 16.dp , end = 16.dp)
    )
}

@Composable
fun DateLayout(state : MutableState<Boolean>, date: Date?){
    Row (modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = {
            state.value = true
        }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Column (modifier = Modifier
            .weight(1f)
            .height(72.dp)) {

            Text("Сделать до" ,
                fontSize = 16.sp ,
                lineHeight = 18.sp ,
                fontWeight = FontWeight(400),
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp , top = 16.dp)
            )

            val sdf = SimpleDateFormat("dd/MM/yyyy")
            Text(if (date != null ) sdf.format(date) else "",
                fontSize = 14.sp ,
                lineHeight = 16.sp ,
                fontWeight = FontWeight(400),
                color = Color(0xFF007AFF),
                modifier = Modifier.padding(start = 16.dp , top = 4.dp)
            )
        }

        Switch(checked = false , onCheckedChange = {
            state.value = true
        } ,
            modifier = Modifier.padding(end = 16.dp))
    }
}

@Composable
fun DeleteLayout(onRemoveClicked: () -> Unit , onBackClicked: () -> Unit){
    Column (modifier = Modifier
        .padding(top = 24.dp)
        .height(56.dp)
        .fillMaxWidth()
    ) {
        HorizontalDivider(thickness = 0.5.dp)

        Row (modifier = Modifier
            .height(55.dp)
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable {
                onRemoveClicked.invoke()
                onBackClicked.invoke()
            },
            verticalAlignment = Alignment.CenterVertically)
        {
            Icon(painter = painterResource(R.drawable.ic_basket) ,
                contentDescription = null ,
                tint = Color(0xFFFF3B30),
                modifier = Modifier.size(24.dp))

            Text("Удалить" ,
                fontSize = 16.sp ,
                lineHeight = 20.sp ,
                fontWeight = FontWeight(400),
                color = Color(0xFFFF3B30),
                modifier = Modifier.padding(start = 16.dp )
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}