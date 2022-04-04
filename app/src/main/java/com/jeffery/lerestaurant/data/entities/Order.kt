package com.jeffery.lerestaurant.data.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeffery.lerestaurant.domain.OrderState
import kotlinx.serialization.Serializable

@Serializable
@Keep
@Entity
data class Order(
    @PrimaryKey(autoGenerate = false)
    val orderId: Long = 1,
    val username: String,
    val ownerUserId: Long? = null,
    val orderDate: String,
    val totalPrice: Double,
    val state: String = OrderState.PENDING.name
)