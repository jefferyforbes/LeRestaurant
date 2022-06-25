package com.jeffery.lerestaurant.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jeffery.lerestaurant.ui.components.BottomNavbar
import com.jeffery.lerestaurant.ui.components.OrderListView
import com.jeffery.lerestaurant.ui.theme.typography

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(paddingValues: PaddingValues, navController: NavController) {
    // Screen after user has logged in.
    // Show the list of confirmed submitted or saved orders

    val scaffoldState = rememberScaffoldState()
    val modifier = Modifier
    val viewModel: MainScreenViewModel = hiltViewModel()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(contentPadding = paddingValues) {
                this.AnimatedVisibility(visible = true) {
                    this.transition
                }
                BottomNavbar(navController)
            }
        },
        scaffoldState = scaffoldState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 58.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth()
                    .padding(6.dp)
            ) {
                Text(
                    text = "Saved Orders",
                    modifier
                        .padding(start = 12.dp)
                        .weight(5f),
                    textAlign = TextAlign.Center,
                    fontSize = typography.h5.fontSize,
//                    textDecoration = TextDecoration.Underline
                )
                IconButton(
                    modifier = modifier
                        .padding(top = 8.dp, end = 24.dp)
                        .fillMaxWidth()
                        .height(70.dp)
                        .weight(1f),
                    onClick = { viewModel.refresh() }
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
                }
            }
            OrderListView(viewModel)
        }
    }
}