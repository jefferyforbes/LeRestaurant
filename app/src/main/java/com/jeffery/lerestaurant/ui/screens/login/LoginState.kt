package com.jeffery.lerestaurant.ui.screens.login

sealed class LoginState {
    object LogOut: LoginState()
    object NotLoggedIn : LoginState()
    object LoggedIn : LoginState()
}