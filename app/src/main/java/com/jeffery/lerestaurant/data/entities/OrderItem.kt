package com.jeffery.lerestaurant.data.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Keep
@Entity
data class OrderItem(
    val course: String,
    val orderId: Long = 1,
    val name: String,
    val count: Int,
    val price: Double,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}