package com.example.yatask.ui.screens.noteInfoScreen.viewComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yatask.utils.Importance

@Composable
fun ImportanceLayout(importance: Importance, onImportanceChanged: (Importance) -> Unit) {
    Column(modifier = Modifier
        .padding(top = 12.dp)
        .height(72.dp)
        .fillMaxWidth()
        .clickable {

        }) {

        Text(
            "Важность",
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )

//        DropDownDemo()

        Text(
            importance.name,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight(400),
            color = Color.Black.copy(alpha = 0.2f),
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )

    }

    HorizontalDivider(
        thickness = 0.5.dp,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    )
}

