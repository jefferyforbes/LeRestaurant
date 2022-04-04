package com.jeffery.lerestaurant.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jeffery.lerestaurant.R
import com.jeffery.lerestaurant.ui.navigation.Screens
import com.jeffery.lerestaurant.ui.screens.login.LoginState
import com.jeffery.lerestaurant.ui.screens.login.LoginViewModel
import com.jeffery.lerestaurant.ui.screens.register.RegisterViewModel
import com.jeffery.lerestaurant.ui.theme.AdditionalShapes
import com.jeffery.lerestaurant.ui.theme.typography

@Composable
fun RegistrationForm(navController: NavController, context: Context) {
    val modifier = Modifier
    val viewModel: RegisterViewModel = hiltViewModel()
    val loginViewModel: LoginViewModel = hiltViewModel()
    val state = loginViewModel.loginState.value
    val additionalShapes = AdditionalShapes()

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var username by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    val showPassword by rememberSaveable {
        mutableStateOf(false)
    }

    // TODO: Add a visual icon to toggle password visibility on

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = true
                ),
            )
        }
        Row {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Ascii
                ),
            )
        }
        Row {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (showPassword)
                    VisualTransformation.None else PasswordVisualTransformation()
            )
        }
        Row {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                singleLine = true
            )
        }
        Row {
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text(text = "Phone number") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
        }

        Button(
            onClick = {
                viewModel.registerUser(name, username, password, email, phoneNumber)
                loginViewModel.onLoginClick(username, password)
                if (state == LoginState.LoggedIn) {
                    navController.navigate(Screens.LoginScreen.route) {
                        Toast.makeText(context, "User Registration Success!", Toast.LENGTH_LONG)
                            .show()
                        popUpTo(Screens.LoginScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            },
            shape = additionalShapes.buttonShape,
            modifier = modifier
                .width(140.dp)
                .padding(top = 14.dp)
        ) {
            Text(stringResource(R.string.button_register), style = typography.h4)
        }
    }
}