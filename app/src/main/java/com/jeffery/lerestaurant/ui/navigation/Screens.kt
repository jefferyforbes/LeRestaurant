package com.jeffery.lerestaurant.ui.navigation

const val ROOT_ROUTE = "ROOT_ROUTE"
const val BASE_ROUTE = "BASE_ROUTE"
const val BOTTOM_APP_BAR_ROUTE = "BOTTOM_APP_BAR_ROUTE"

sealed class Screens(val route: String) {
    object LoginScreen : Screens("login_screen")
    object RegisterScreen : Screens("register_screen")
    object MainScreen : Screens("main_screen")
    object MenuScreen : Screens("menu_screen")
    object ContactScreen : Screens("contact_screen")
    object OrderScreen : Screens("order_screen")
    object BookingScreen : Screens("booking_screen")
    object PastOrdersScreen : Screens("past_orders_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}