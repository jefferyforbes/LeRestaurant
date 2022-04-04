package com.jeffery.lerestaurant.data.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeffery.lerestaurant.domain.UserType
import kotlinx.serialization.Serializable

@Serializable
@Keep
@Entity
data class User(
    val name: String,
    val username: String,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val authority: UserType = UserType.PERSONAL
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Long? = null
}