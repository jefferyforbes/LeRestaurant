package com.jeffery.lerestaurant.ui.screens.login

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jeffery.lerestaurant.ui.components.LoginForm

@Composable
fun LoginScreen(
    navController: NavController,
    context: Context,
) {
    val modifier = Modifier
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO: Add logo
        val viewModel: LoginViewModel = hiltViewModel()
        LoginForm(navController, context, viewModel)
    }
}