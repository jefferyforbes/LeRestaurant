package com.jeffery.lerestaurant.data.repositories

import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.entities.OrderItem
import kotlinx.coroutines.flow.Flow

interface CurrentOrderRepository {

    fun observeMenuItems(): Flow<List<MenuItem>>

    suspend fun saveCurrentOrder(items: List<OrderItem>)
}