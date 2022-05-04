package com.jeffery.lerestaurant.data.repositories

import android.app.Application
import com.jeffery.lerestaurant.data.AppDatabase
import com.jeffery.lerestaurant.data.entities.MenuItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    application: Application
) : MenuRepository {

    private val menuDao = AppDatabase.getDatabase(application).menuDao()

    override fun observeMenuItems(): Flow<List<MenuItem>> {
        return menuDao.getAllMenuItems()
    }

    override suspend fun getMenuItem(menuItem: MenuItem): MenuItem? {
        return menuDao.getAllMenuItems().first().find {
            it.menuItemId == menuItem.menuItemId
        }
    }

    override suspend fun updateMenuItem(menuItem: MenuItem) {
        menuItem.menuItemId?.let { menuDao.updateMenuItemCount(menuItem.itemCount++, it) }
    }
}