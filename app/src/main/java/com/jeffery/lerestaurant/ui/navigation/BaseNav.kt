package com.jeffery.lerestaurant.ui.navigation

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jeffery.lerestaurant.ui.screens.BookingScreen
import com.jeffery.lerestaurant.ui.screens.contact.ContactScreen
import com.jeffery.lerestaurant.ui.screens.currentorder.CurrentOrderScreen
import com.jeffery.lerestaurant.ui.screens.login.LoginScreen
import com.jeffery.lerestaurant.ui.screens.main.MainScreen
import com.jeffery.lerestaurant.ui.screens.menu.MenuScreen
import com.jeffery.lerestaurant.ui.screens.menu.MenuViewModel
import com.jeffery.lerestaurant.ui.screens.register.RegisterScreen

fun NavGraphBuilder.baseNav(
    navController: NavController,
    paddingValues: PaddingValues,
    application: Application,
    menuViewModel: MenuViewModel
) {
    navigation(
        startDestination = Screens.MainScreen.route, // Should be login after testing
        route = BASE_ROUTE
    ) {
        val navHostController = navController as NavHostController

        composable(Screens.LoginScreen.route) {
            LoginScreen(navController, application.applicationContext)
        }

        composable(Screens.RegisterScreen.route) {
            RegisterScreen(navController, application.applicationContext)
        }

        composable(Screens.MainScreen.route) { MainScreen(paddingValues, navController) }

        composable(Screens.MenuScreen.route) {
            MenuScreen(
                paddingValues,
                navController,
                application.applicationContext,
                menuViewModel
            )
        }

        composable(Screens.OrderScreen.route) {
            CurrentOrderScreen(
                paddingValues,
                navController
            )
        }

        composable(Screens.BookingScreen.route) { BookingScreen(paddingValues, navController) }

        composable(Screens.ContactScreen.route) { ContactScreen(paddingValues, navController) }
    }
}