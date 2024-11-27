package com.example.yatask.ui.screens.noteInfoScreen.viewComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yatask.R

@Composable
fun CustomAppBar(saveClicked: (id: String) -> Unit, onBackClicked: () -> Unit, id: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFF7F6F2)),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Icon(painter = painterResource(R.drawable.ic_fab),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(56.dp)
                .clickable {
                    onBackClicked.invoke()
                }
                .padding(16.dp)
                .rotate(45f)

        )
        Spacer(Modifier.weight(1f))

        Text(
            modifier = Modifier
                .height(56.dp)
                .clickable {
                    saveClicked.invoke(id)
                    onBackClicked.invoke()
                }
                .padding(end = 16.dp, start = 16.dp)
                .wrapContentHeight(align = Alignment.CenterVertically),
            text = "Сохранить".uppercase(),
            color = Color(0xFF007AFF),
            fontSize = 14.sp,
            lineHeight = 24.sp,
        )

    }


}
