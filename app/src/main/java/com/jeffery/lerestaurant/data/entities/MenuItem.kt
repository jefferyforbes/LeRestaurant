package com.jeffery.lerestaurant.data.entities

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Keep
@Entity
data class MenuItem(
    val course: String,
    val name: String,
    val itemCount: Int = 0,
    val price: Double,
    val stock: Int
) {
    @PrimaryKey(autoGenerate = true)
    var menuItemId: Int? = null
}
