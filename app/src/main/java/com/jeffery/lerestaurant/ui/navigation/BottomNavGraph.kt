package com.jeffery.lerestaurant.ui.navigation

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jeffery.lerestaurant.ui.screens.BookingScreen
import com.jeffery.lerestaurant.ui.screens.contact.ContactScreen
import com.jeffery.lerestaurant.ui.screens.currentorder.CurrentOrderScreen
import com.jeffery.lerestaurant.ui.screens.currentorder.CurrentOrderViewModel
import com.jeffery.lerestaurant.ui.screens.main.MainScreen
import com.jeffery.lerestaurant.ui.screens.menu.MenuScreen
import com.jeffery.lerestaurant.ui.screens.menu.MenuViewModel

@Composable
fun BottomNavGraph(
    paddingValues: PaddingValues,
    navController: NavController,
    application: Application,
    menuViewModel: MenuViewModel,
    currentOrderViewModel: CurrentOrderViewModel
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
                application.applicationContext,
                menuViewModel
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