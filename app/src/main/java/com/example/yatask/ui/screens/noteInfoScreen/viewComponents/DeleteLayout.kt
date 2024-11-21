package com.example.yatask.ui.screens.noteInfoScreen.viewComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yatask.R


@Composable
fun DeleteLayout(onRemoveClicked: () -> Unit, onBackClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(top = 24.dp)
            .height(56.dp)
            .fillMaxWidth()
    ) {
        HorizontalDivider(thickness = 0.5.dp)

        Row(
            modifier = Modifier
                .height(55.dp)
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .clickable {
                    onRemoveClicked.invoke()
                    onBackClicked.invoke()
                },
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                painter = painterResource(R.drawable.ic_basket),
                contentDescription = null,
                tint = Color(0xFFFF3B30),
                modifier = Modifier.size(24.dp)
            )

            Text(
                "Удалить",
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFF3B30),
                modifier = Modifier.padding(start = 16.dp)
            )

        }
    }
}
