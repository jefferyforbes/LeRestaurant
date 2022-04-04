package com.jeffery.lerestaurant.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jeffery.lerestaurant.ui.navigation.BottomBarScreens

@Composable
fun BottomNavbar(navController: NavController) {
    val screens = listOf(
        BottomBarScreens.Main,
        BottomBarScreens.Menu,
        BottomBarScreens.Order,
        BottomBarScreens.Booking,
        BottomBarScreens.Contact
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        elevation = 8.dp
    ) {
        navController as NavHostController
        screens.forEach { screen ->
            AddItem(
                screens = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}