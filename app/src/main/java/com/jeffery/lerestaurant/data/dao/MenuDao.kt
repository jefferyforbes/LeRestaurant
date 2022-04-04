package com.jeffery.lerestaurant.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.entities.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Query("SELECT * FROM `MenuItem`")
    fun getAll(): Flow<List<MenuItem>>

    @Insert
    suspend fun insertAll(menuItems: List<MenuItem>)

    @Transaction
    @Query("UPDATE menuitem SET itemCount = :count WHERE menuItemId = :menuItemId")
    fun updateMenuItemCount(count: Int, menuItemId: Int)
}