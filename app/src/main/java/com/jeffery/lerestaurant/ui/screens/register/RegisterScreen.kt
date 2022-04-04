package com.jeffery.lerestaurant.ui.screens.register

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jeffery.lerestaurant.R
import com.jeffery.lerestaurant.ui.components.RegistrationForm
import com.jeffery.lerestaurant.ui.theme.typography

@Composable
fun RegisterScreen(navController: NavController, context: Context) {
    val modifier = Modifier
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier.padding(8.dp, 48.dp, 8.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = stringResource(R.string.registration_title), style = typography.h2)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RegistrationForm(navController, context)
        }
    }
}