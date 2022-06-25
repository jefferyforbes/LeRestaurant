package com.jeffery.lerestaurant

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.jeffery.lerestaurant.data.AppDatabase
import com.jeffery.lerestaurant.data.repositories.MenuItemDatasource
import com.jeffery.lerestaurant.ui.navigation.AppNav
import com.jeffery.lerestaurant.ui.screens.menu.MenuViewModel
import com.jeffery.lerestaurant.ui.theme.LeRestaurantTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : AppCompatActivity() {

    /*
    TODO: The main activity should only be for register and login, I need to create a "BaseActivity"
    for the logged in state that drops you in the main screen of the app.
     */

    private val menuViewModel: MenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val menuDao = AppDatabase.getDatabase(this).menuDao()
        lifecycleScope.launch {
            val items = menuDao.getAllMenuItems().asLiveData().value
            if (items.isNullOrEmpty()) {
                menuDao.deleteAll()
                menuDao.insertAll(MenuItemDatasource().restaurantMenuItems)
            }
        }

        setContent {
            LeRestaurantTheme {
                Scaffold { values ->
                    AppNav(
                        values,
                        application,
                        menuViewModel
                    )
                }
            }
        }
    }
}
