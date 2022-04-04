package com.jeffery.lerestaurant.ui.navigation

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jeffery.lerestaurant.ui.screens.BookingScreen
import com.jeffery.lerestaurant.ui.screens.ContactScreen
import com.jeffery.lerestaurant.ui.screens.currentOrder.CurrentOrderScreen
import com.jeffery.lerestaurant.ui.screens.mainScreen.MainScreen
import com.jeffery.lerestaurant.ui.screens.menu.MenuScreen

@Composable
@Deprecated("Just use appnav and base nav")
fun BottomNavGraph(
    paddingValues: PaddingValues,
    navController: NavController,
    application: Application
) {
    navController as NavHostController
    NavHost(
        navController,
        startDestination = BottomBarScreens.Main.route,
        route = BOTTOM_APP_BAR_ROUTE
    ) {
        composable(BottomBarScreens.Main.route) { MainScreen(paddingValues, navController) }
        composable(BottomBarScreens.Menu.route) {
            MenuScreen(
                paddingValues,
                navController,
                application.applicationContext
            )
        }
        composable(BottomBarScreens.Order.route) {
            CurrentOrderScreen(
                paddingValues,
                navController
            )
        }
        composable(BottomBarScreens.Booking.route) {
            BookingScreen(
                paddingValues,
                navController
            )
        }
        composable(BottomBarScreens.Contact.route) {
            ContactScreen(
                paddingValues,
                navController
            )
        }
    }
}