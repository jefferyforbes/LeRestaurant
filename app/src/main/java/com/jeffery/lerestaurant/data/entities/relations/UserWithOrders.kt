package com.jeffery.lerestaurant.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.jeffery.lerestaurant.data.entities.Order
import com.jeffery.lerestaurant.data.entities.User

data class UserWithOrders(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "ownerUserId"
    )
    val orders: List<Order>
)