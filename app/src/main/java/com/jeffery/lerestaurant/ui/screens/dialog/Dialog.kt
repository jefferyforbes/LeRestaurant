package com.jeffery.lerestaurant.ui.screens.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.jeffery.lerestaurant.ui.theme.typography

@Composable
fun Dialog(
    state: MutableState<Boolean>,
    title: String,
    content: @Composable (() -> Unit)? = null,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val modifier = Modifier
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.value) {
            AlertDialog(
                title = {
                    Text(
                        text = title,
                        fontSize = 22.sp,
                        fontStyle = typography.body1.fontStyle,
                        fontWeight = FontWeight.Light,
                        textDecoration = TextDecoration.Underline
                    )
                },
                text = content,
                onDismissRequest = { state.value = false },
                confirmButton = {
                    Button(
                        modifier = modifier.fillMaxWidth(),
                        onClick = {
                            onConfirm
                            state.value = false
                        }) { Text(text = "Done") }
                }
            )
        }
    }
}