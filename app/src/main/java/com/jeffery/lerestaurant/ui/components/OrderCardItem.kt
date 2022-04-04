package com.jeffery.lerestaurant.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrderCardItem(key: String, value: String) {
    val modifier = Modifier
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.wrapContentSize()
    ) {
        Text(
            text = key,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = modifier.padding(end = 2.dp)
        )
        Text(
            text = value,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = modifier
                .wrapContentSize()
                .padding(top = 4.dp)
        )
    }
}