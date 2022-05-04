package com.jeffery.lerestaurant

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import com.jeffery.lerestaurant.ui.navigation.AppNav
import com.jeffery.lerestaurant.ui.screens.menu.MenuViewModel
import com.jeffery.lerestaurant.ui.theme.LeRestaurantTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : AppCompatActivity() {

    private val menuViewModel: MenuViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeRestaurantTheme { Scaffold { values -> AppNav(values, application, menuViewModel) } }
        }
    }
}
