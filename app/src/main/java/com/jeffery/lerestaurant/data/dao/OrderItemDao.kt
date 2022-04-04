package com.jeffery.lerestaurant.data.dao

import androidx.room.*
import com.jeffery.lerestaurant.data.entities.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewOrderItem(orderItem: OrderItem)

    @Query("SELECT * FROM orderitem")
    fun getAllOrderItems(): Flow<List<OrderItem>>

    @Transaction
    @Query("DELETE FROM orderitem")
    fun resetCurrentOrders()

    @Transaction
    @Query("DELETE FROM orderitem WHERE id = :orderItemId")
    fun removeOneOrderItem(orderItemId: Long)
}