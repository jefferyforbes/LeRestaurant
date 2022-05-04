package com.jeffery.lerestaurant.data.repositories

import com.jeffery.lerestaurant.data.entities.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    fun observeMenuItems() : Flow<List<MenuItem>>

    suspend fun getMenuItem(menuItem: MenuItem) : MenuItem?

    suspend fun updateMenuItem(menuItem: MenuItem)
}