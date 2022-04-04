package com.jeffery.lerestaurant.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jeffery.lerestaurant.ui.screens.menu.MenuViewModel

@Composable
fun MenuItemListView(modifier: Modifier, context: Context, viewModel: MenuViewModel) {
    val state = viewModel.menuState.value
    var menuList = viewModel.allMenuItems

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        LazyColumn {
            items(menuList) { menuItem ->
                MenuItemView(
                    modifier = modifier,
                    menuItem = menuItem,
                    context,
                    viewModel
                )
            }
        }
    }

    when (state) {
        MenuViewModel.MenuListState.Content -> {
            menuList = viewModel.allMenuItems
        }
        MenuViewModel.MenuListState.Idle -> {
            Unit
        }
        MenuViewModel.MenuListState.Loading -> {
            viewModel.refresh()
        }
    }
}