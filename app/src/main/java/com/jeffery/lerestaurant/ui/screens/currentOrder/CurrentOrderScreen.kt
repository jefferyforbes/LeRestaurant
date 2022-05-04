package com.jeffery.lerestaurant.ui.screens.currentOrder

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.ui.components.BottomNavbar

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CurrentOrderScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
) {
    val modifier = Modifier
    val currentOrderViewModel: CurrentOrderViewModel = hiltViewModel()
    val state by currentOrderViewModel.state.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
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
            modifier = modifier
                .fillMaxSize()
        ) {
            CurrentOrderView(
                modifier,
                currentOrderViewModel,
                state.items,

            )
        }
    }
}

@Composable
fun CurrentOrderView(modifier: Modifier, viewModel: CurrentOrderViewModel, items: List<MenuItem>) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .wrapContentSize()
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        items.forEach { item ->
            CurrentOrderItemView(item)
            Spacer(modifier = modifier.padding(8.dp))
        }
        Button(onClick = { viewModel.saveOrder() }) {
            Text(text = "Save Order")
        }
    }
}

@Composable
fun CurrentOrderItemView(item: MenuItem) {
    val modifier = Modifier
    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(4.dp),
        ) {
        Column(
            modifier = modifier
                .width(280.dp)
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Text(text = "Course name: ")
                Text(text = item.course)
            }
            Row {
                Text(text = "Item name: ")
                Text(text = item.name)
            }
            Row {
                Text(text = "Item price: ")
                Text(text = "£" + item.price)
            }
        }
    }
}