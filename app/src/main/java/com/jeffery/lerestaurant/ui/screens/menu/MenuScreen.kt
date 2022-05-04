package com.jeffery.lerestaurant.ui.screens.menu

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomAppBar
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import androidx.navigation.NavHostController
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.domain.CourseType
import com.jeffery.lerestaurant.ui.components.BottomNavbar
import com.jeffery.lerestaurant.ui.components.FullScreenLoader
import com.jeffery.lerestaurant.ui.components.MenuItemListView
import com.jeffery.lerestaurant.ui.screens.currentOrder.CurrentOrderViewModel
import com.jeffery.lerestaurant.ui.theme.typography

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MenuScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    context: Context,
    viewModel: MenuViewModel
) {
    val modifier = Modifier
    val state by viewModel.state.collectAsState()

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
                .padding(bottom = 58.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading) {
                FullScreenLoader()
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Menu",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = typography.h5.fontSize
                    )
                }
                MenuItemListView(
                    modifier = modifier,
                    context = context,
                    viewModel = viewModel,
                    menuList = state.itemList
                )
            }
        }
    }
}