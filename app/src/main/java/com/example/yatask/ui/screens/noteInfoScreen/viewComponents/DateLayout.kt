package com.example.yatask.ui.screens.noteInfoScreen.viewComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun DateLayout(state: MutableState<Boolean>, date: Date?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                state.value = true
            }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .height(72.dp)
        ) {

            Text(
                "Сделать до",
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(400),
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )

            val sdf = SimpleDateFormat("dd/MM/yyyy")
            Text(
                if (date != null) sdf.format(date) else "",
                fontSize = 14.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF007AFF),
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Switch(checked = false, onCheckedChange = {
            state.value = true
        },
            modifier = Modifier.padding(end = 16.dp))
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