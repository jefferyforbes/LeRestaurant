package com.jeffery.lerestaurant.ui.screens.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeffery.lerestaurant.data.LeRestaurantService
import com.jeffery.lerestaurant.domain.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val leRestaurantService: LeRestaurantService
) : ViewModel() {

    private val _loginState = mutableStateOf<LoginState>(LoginState.NotLoggedIn)
    var loginState: MutableState<LoginState> = _loginState

    fun onLoginClick(username: String, password: String) {
        viewModelScope.launch {
            leRestaurantService.login(username, password).collectLatest { state ->
                when (state) {
                    is ResponseState.Failure -> {
                        Log.d("Login state: ", state.message!! + "Error: Finding user")
                    }
                    ResponseState.Success -> {
                        leRestaurantService.setCurrentUsername(username)
                        _loginState.value = LoginState.LoggedIn
                        Log.d("Login state: ", state.toString())
                    }
                }
            }
        }
    }
}