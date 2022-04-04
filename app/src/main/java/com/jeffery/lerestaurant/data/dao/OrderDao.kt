package com.jeffery.lerestaurant.data.dao

import androidx.compose.runtime.MutableState
import androidx.room.*
import com.jeffery.lerestaurant.data.entities.Order
import com.jeffery.lerestaurant.data.entities.OrderItem
import com.jeffery.lerestaurant.data.entities.relations.OrderWithOrderItems
import com.jeffery.lerestaurant.data.entities.relations.UserWithOrders
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Transaction
    @Query("SELECT * FROM `order`")
    fun getAllOrders(): Flow<List<Order>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewOrder(order: Order)

    @Transaction
    @Query("SELECT * FROM user WHERE userId = :userId")
    fun getUserOrders(userId: Long): Flow<List<UserWithOrders>>

    @Transaction
    @Query("SELECT * FROM `order` WHERE orderId = :orderId")
    fun getOrderItems(orderId: Long): Flow<List<OrderWithOrderItems>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveOrderItem(orderItem: OrderItem)
}