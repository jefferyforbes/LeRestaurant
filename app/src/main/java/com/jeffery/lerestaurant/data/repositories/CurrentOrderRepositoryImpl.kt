package com.jeffery.lerestaurant.data.repositories

import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.entities.OrderItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrentOrderRepositoryImpl @Inject constructor(
    private val menuRepository: MenuRepository
): CurrentOrderRepository {
    override fun observeMenuItems(): Flow<List<MenuItem>> {
       return menuRepository.observeMenuItems()
    }

    override suspend fun saveCurrentOrder(items: List<OrderItem>) {
        TODO("Not yet implemented")
    }
}