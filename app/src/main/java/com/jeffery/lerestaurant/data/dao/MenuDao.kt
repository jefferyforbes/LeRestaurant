package com.jeffery.lerestaurant.data.dao

import androidx.room.*
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.entities.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Query("SELECT * FROM `MenuItem`")
    fun getAllMenuItems(): Flow<List<MenuItem>>

    @Insert
    suspend fun insertAll(menuItems: List<MenuItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneMenuItem(menuItem: MenuItem)

    @Delete
    suspend fun deleteOneMenuItem(menuItem: MenuItem)

    @Transaction
    @Query("UPDATE menuitem SET itemCount = :count WHERE menuItemId = :menuItemId")
    suspend fun updateMenuItemCount(count: Int, menuItemId: Int)
}