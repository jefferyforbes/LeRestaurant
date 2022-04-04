package com.jeffery.lerestaurant.data

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.entities.Order
import com.jeffery.lerestaurant.data.entities.OrderItem
import com.jeffery.lerestaurant.data.entities.User
import com.jeffery.lerestaurant.domain.ResponseState
import com.jeffery.lerestaurant.domain.TableBooking
import kotlinx.coroutines.flow.Flow

interface LeRestaurantService {

    suspend fun login(username: String, password: String): Flow<ResponseState>

    suspend fun register(user: User): User

    suspend fun getAllOrders(): Flow<List<Order>>

    suspend fun getAllMenuItems(): Flow<List<MenuItem>>

    suspend fun saveOrder(totalPrice: String, orderItems: List<OrderItem>): Order

    suspend fun bookTable(table: TableBooking): TableBooking

    suspend fun insertStockData(menuItems: List<MenuItem>)

    suspend fun getTableBookings(table: TableBooking)

    suspend fun updateStock()

    fun getCurrentUsername(): String

    fun setCurrentUsername(username: String)

    suspend fun deleteAllItemsInDb()

    fun updateMenuItemCount(count: Int, menuItemId: Int)

    fun getAllCurrentOrders(): Flow<List<OrderItem>>

    fun addCurrentOrderItem(orderItem: OrderItem)

    fun removeCurrentOrder()

    fun removeOneOrderItem(orderItem: OrderItem)
}