package com.jeffery.lerestaurant.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.ui.screens.menu.MenuViewModel

@Composable
fun MenuItemListView(
    modifier: Modifier,
    context: Context,
    viewModel: MenuViewModel,
    menuList: List<MenuItem>
) {

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
                    viewModel,
                    { menuItem.menuItemId?.let { viewModel.interactWithMenuItem(it) } },
                    { viewModel.refresh() }
                )
            }
        }
    }
}