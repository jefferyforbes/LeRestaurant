package com.jeffery.lerestaurant.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.jeffery.lerestaurant.data.entities.Order
import com.jeffery.lerestaurant.data.entities.OrderItem

data class OrderWithOrderItems(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "orderId"
    )
    val orderItems: List<OrderItem>
)