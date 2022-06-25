package com.jeffery.lerestaurant.ui.screens.currentorder

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    navHostController: NavHostController
) {
    val viewModel: CurrentOrderViewModel = hiltViewModel()
    val modifier = Modifier
    val state by viewModel.state.collectAsState()

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
                .padding(bottom = 60.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CurrentOrderView(
                modifier,
                viewModel,
                state.items,
            )
            Button(onClick = { viewModel.saveOrder() }, modifier = modifier.padding(8.dp)) {
                Text(text = "Save Order")
            }
        }
    }
}

@Composable
fun CurrentOrderView(
    modifier: Modifier,
    viewModel: CurrentOrderViewModel,
    itemsList: List<MenuItem>
) {
    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                items(itemsList) { item ->
                    CurrentOrderItemView(item)
                    Spacer(modifier = modifier.padding(8.dp))
                }
            }
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
                .width(340.dp)
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Text(text = "Course name: ")
                Text(text = item.course.replaceFirstChar { it.titlecase() })
            }
            Row {
                Text(text = "Item name: ")
                Text(text = item.name)
            }
            Row {
                Text(text = "Item price: ")
                Text(text = "£" + item.price)
            }
            Row {
                Text(text = "Item count: ")
                Text(text = item.itemCount.toString())
            }
        }
    }
}