package com.jeffery.lerestaurant.ui.screens.contact

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jeffery.lerestaurant.ui.components.BottomNavbar

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContactScreen(paddingValues: PaddingValues, navHostController: NavHostController) {

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
            Text(text = "Contact Screen")
        }
    }
}