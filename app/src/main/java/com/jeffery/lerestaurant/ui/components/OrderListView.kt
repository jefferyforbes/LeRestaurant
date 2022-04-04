package com.jeffery.lerestaurant.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jeffery.lerestaurant.ui.screens.mainScreen.MainScreenViewModel

@Composable
fun OrderListView(viewModel: MainScreenViewModel) {
    val modifier = Modifier
    val state = viewModel.orderListState.value
    var orders = viewModel.allOrders

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        LazyColumn {
            items(orders) { order ->
                OrderCardView(modifier = modifier, order = order)
            }
        }
    }

    when (state) {
        MainScreenViewModel.OrderListState.Content -> {
            orders = viewModel.allOrders
        }
        MainScreenViewModel.OrderListState.Idle -> {
            Unit
        }
        MainScreenViewModel.OrderListState.Loading -> {
            viewModel.refresh()
        }
    }
}