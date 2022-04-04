package com.jeffery.lerestaurant

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import com.jeffery.lerestaurant.ui.navigation.AppNav
import com.jeffery.lerestaurant.ui.theme.LeRestaurantTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeRestaurantTheme { Scaffold { values -> AppNav(values, application) } }
        }
    }
}
