package com.jeffery.lerestaurant.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jeffery.lerestaurant.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM User ORDER BY userId")
    fun getAll(): Flow<List<User>>

    @Transaction
    @Query("SELECT * FROM User WHERE userId IN (:userIds)")
    fun findUserByIds(userIds: LongArray): Flow<List<User>>

    @Transaction
    @Query("SELECT * FROM User WHERE username = :username LIMIT 1")
    fun findUserByName(username: String): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM MenuItem")
    suspend fun deleteAllItemsInMenu()

    @Query("DELETE FROM User")
    suspend fun deleteAllItemsInUser()

    @Query("DELETE FROM `Order`")
    suspend fun deleteAllItemsInOrder()

    @Query("DELETE FROM `OrderItem`")
    suspend fun deleteAllItemsInOrderItem()
}