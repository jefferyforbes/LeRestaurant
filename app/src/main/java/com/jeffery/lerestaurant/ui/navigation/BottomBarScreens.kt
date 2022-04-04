package com.jeffery.lerestaurant.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Main : BottomBarScreens(
        route = Screens.MainScreen.route,
        title = "Main",
        icon = Icons.Default.Home
    )

    object Menu : BottomBarScreens(
        route = Screens.MenuScreen.route,
        title = "Menu",
        icon = Icons.Default.Menu
    )

    object Order : BottomBarScreens(
        route = Screens.OrderScreen.route,
        title = "Order",
        icon = Icons.Default.ShoppingCart
    )

    object Booking : BottomBarScreens(
        route = Screens.BookingScreen.route,
        title = "Booking",
        icon = Icons.Default.AddCircle // TODO: Check?
    )

    object Contact : BottomBarScreens(
        route = Screens.ContactScreen.route,
        title = "Contact",
        icon = Icons.Default.Person
    )
}
