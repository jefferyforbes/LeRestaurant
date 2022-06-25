package com.jeffery.lerestaurant.ui.screens

import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jeffery.lerestaurant.ui.components.BottomNavbar
import com.jeffery.lerestaurant.ui.navigation.BottomNavGraph

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BookingScreen(paddingValues: PaddingValues, navHostController: NavHostController) {

    val restaurantName = "Le Restaurant"

    Scaffold(
        bottomBar = {
            BottomAppBar(contentPadding = paddingValues) {
                this.AnimatedVisibility(visible = true) {
                    this.transition
                }
                BottomNavbar(navHostController)

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Row() {
                Text(text = "Booking Screen")
            }
        }
    }
}