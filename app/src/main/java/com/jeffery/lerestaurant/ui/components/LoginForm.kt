package com.jeffery.lerestaurant.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jeffery.lerestaurant.R
import com.jeffery.lerestaurant.ui.navigation.Screens
import com.jeffery.lerestaurant.ui.screens.login.LoginState
import com.jeffery.lerestaurant.ui.screens.login.LoginViewModel
import com.jeffery.lerestaurant.ui.theme.AdditionalShapes
import com.jeffery.lerestaurant.ui.theme.typography

@Composable
fun LoginForm(
    navController: NavController,
    context: Context,
    viewModel: LoginViewModel,
) {
    navController as NavHostController
    val modifier = Modifier
    val state = viewModel.loginState.value
    val additionalShapes = AdditionalShapes()

    var showPassword by rememberSaveable {
        mutableStateOf(false)
    }

    var username by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TODO: Add the logo resource here
        Row(
            modifier.padding(4.dp, 4.dp, 4.dp, 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = stringResource(id = R.string.login_title), style = typography.h2)
        }

        Column(
            modifier
                .padding(start = 58.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Ascii,
                        imeAction = ImeAction.Next
                    )
                )
            }

            Row(
            ) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Ascii,
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = if (showPassword)
                        VisualTransformation.None else PasswordVisualTransformation(),
                )
                IconButton(
                    onClick = {
                        if (!showPassword) {
                            showPassword = true
                        } else if (showPassword) {
                            showPassword = false
                        }
                    }) {
                    Icon(
                        modifier = modifier.padding(start = 8.dp, top = 18.dp),
                        painter = if (!showPassword)
                            painterResource(id = R.drawable.ic_baseline_visibility_24)
                        else
                            painterResource(id = R.drawable.ic_baseline_visibility_off_24),
                        contentDescription = "visibility icon"
                    )
                }
            }
        }

        Row(
            modifier = modifier
                .width(140.dp)
                .padding(top = 8.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = {
                    // TODO: Fix delay button response
                    viewModel.onLoginClick(username, password)
                    if (state == LoginState.LoggedIn) {
                        Toast.makeText(context, "Login Success!", Toast.LENGTH_LONG)
                            .show()
                    }
                },
                shape = additionalShapes.smallButtonShape,
                modifier = modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth(),
            ) {
                Text("login", style = typography.h6)
            }
        }

        Row(
            modifier = modifier
                .width(140.dp)
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = { navController.navigate(Screens.RegisterScreen.route) },
                shape = additionalShapes.smallButtonShape,
                modifier = modifier
                    .padding(top = 18.dp)
                    .fillMaxWidth(),
            ) {
                Text("register", style = typography.h6)
            }
        }
    }

    when (state) {
        LoginState.LoggedIn -> {
            navController.navigate(Screens.MainScreen.route) {
                launchSingleTop = true
                restoreState = true
                popUpToRoute
            }
        }
        LoginState.NotLoggedIn -> {
            Unit
        }
        LoginState.LogOut -> navController.navigate(Screens.LoginScreen.route) {
            launchSingleTop = true
            restoreState = true
            popUpToRoute
        }
    }
}
