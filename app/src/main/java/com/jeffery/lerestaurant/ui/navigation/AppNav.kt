package com.jeffery.lerestaurant.ui.navigation

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jeffery.lerestaurant.ui.screens.menu.MenuViewModel

@Composable
fun AppNav(
    paddingValues: PaddingValues,
    application: Application,
    menuViewModel: MenuViewModel
) {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = BASE_ROUTE,
        route = ROOT_ROUTE
    ) {
        baseNav(navHostController, paddingValues, application, menuViewModel)
    }
}