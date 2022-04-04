package com.jeffery.lerestaurant.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeffery.lerestaurant.data.LeRestaurantService
import com.jeffery.lerestaurant.data.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val leRestaurantService: LeRestaurantService
) : ViewModel() {

    // TODO: Add Register state to notify the user through snackbar if the request fails

    fun registerUser(
        name: String,
        username: String,
        password: String,
        email: String,
        phoneNumber: String
    ) {
        viewModelScope.launch {
            val newUser = User(
                name = name,
                username = username,
                password = password,
                email = email,
                phoneNumber = phoneNumber
            )
            leRestaurantService.register(newUser)
        }
    }
}