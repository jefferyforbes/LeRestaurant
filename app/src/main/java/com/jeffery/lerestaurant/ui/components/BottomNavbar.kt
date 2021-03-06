package com.jeffery.lerestaurant.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
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

@Composable
fun RowScope.AddItem(
    screens: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        modifier = Modifier.fillMaxSize(),
        icon = {
            Icon(imageVector = screens.icon, contentDescription = "Navigation Icon")
        },
        label = { Text(text = screens.title) },
        selected = currentDestination?.hierarchy?.any { it.route == screens.route } == true,
        onClick = {
            navController.navigate(screens.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                restoreState = true
                launchSingleTop = true
            }
        },
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
    )
}