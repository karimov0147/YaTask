package com.example.yatask.ui.screens.noteInfoScreen.viewComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteLayout(text: String, onTextChanged: (String) -> Unit) {
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
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(fontSize = 16.sp),
        placeholder = {
            Text(
                "Что надо сделать...",
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 16.sp,
                color = Color.Black.copy(alpha = 0.3f),
                modifier = Modifier
            )
        },
    )
}
