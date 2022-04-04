package com.jeffery.lerestaurant.ui.screens.currentOrder

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jeffery.lerestaurant.data.entities.OrderItem
import com.jeffery.lerestaurant.ui.components.BottomNavbar
import com.jeffery.lerestaurant.ui.theme.AdditionalShapes

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CurrentOrderScreen(paddingValues: PaddingValues, navHostController: NavHostController) {
    val modifier = Modifier
    val viewModel: CurrentOrderViewModel = hiltViewModel()
    val state = viewModel.currentOrderState.value
    var items = viewModel.currentOrderList

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
            CurrentOrderView(modifier, viewModel, items)
        }
    }

    when (state) {
        // TODO: Add snack bar toast for empty fields and max items state
        CurrentOrderViewModel.CurrentOrderState.EmptyFields -> {
            Unit
        }
        CurrentOrderViewModel.CurrentOrderState.Idle -> {
            Unit
        }
        CurrentOrderViewModel.CurrentOrderState.Loading -> {
            viewModel.refresh()
        }
        CurrentOrderViewModel.CurrentOrderState.MaxCurrentItems -> {
            Unit
        }
        CurrentOrderViewModel.CurrentOrderState.Content -> {
            items = viewModel.currentOrderList
        }
    }
}

@Composable
fun CurrentOrderView(modifier: Modifier, viewModel: CurrentOrderViewModel, items: List<OrderItem>) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .wrapContentSize()
            .fillMaxWidth()
            .padding(top = 2.dp)
    ) {
        items.forEach { item ->
            CurrentOrderItemView(item, viewModel)
            Spacer(modifier = modifier.padding(6.dp))
        }
        Button(onClick = { viewModel.saveOrder() }) {
            Text(text = "Save Order")
        }
    }
}

@Composable
fun CurrentOrderItemView(item: OrderItem, viewModel: CurrentOrderViewModel) {
    val modifier = Modifier
    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(4.dp),
        ) {
        Column(
            modifier = modifier
                .wrapContentSize()
                .padding(12.dp),
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