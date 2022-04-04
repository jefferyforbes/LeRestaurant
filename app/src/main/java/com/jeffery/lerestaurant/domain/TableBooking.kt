package com.jeffery.lerestaurant.domain

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TableBooking(
    val id: String,
    val tableNumber: String
)
