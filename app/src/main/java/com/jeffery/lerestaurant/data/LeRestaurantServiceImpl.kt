package com.jeffery.lerestaurant.data

import android.app.Application
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.entities.Order
import com.jeffery.lerestaurant.data.entities.OrderItem
import com.jeffery.lerestaurant.data.entities.User
import com.jeffery.lerestaurant.domain.ResponseState
import com.jeffery.lerestaurant.domain.TableBooking
import com.jeffery.lerestaurant.util.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class LeRestaurantServiceImpl @Inject constructor(
    application: Application
) : LeRestaurantService {

    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val menuDao = AppDatabase.getDatabase(application).menuDao()
    private val orderDao = AppDatabase.getDatabase(application).orderDao()

    private var currentUsername: String = "adminUser"

    override suspend fun login(username: String, password: String): Flow<ResponseState> {
        val selectedUser = userDao.findUserByName(username).firstOrNull()
        return if (selectedUser?.username == username && selectedUser.password == password) {
            flowOf(ResponseState.Success)
        } else flowOf(ResponseState.Failure(message = "User not found"))
    }

    override suspend fun register(user: User): User {
        userDao.insertUser(user)
        return user
    }

    override suspend fun saveOrder(totalPrice: String, orderItems: List<OrderItem>): Order =
        withContext(Dispatchers.IO) {
            val orderId = Random().nextLong()
            val sdf = SimpleDateFormat.getDateInstance()
            val currentDate = sdf.format(Date())

            orderItems.forEach { orderItem ->
                orderDao.saveOrderItem(orderItem)
            }

            val newOrder = Mapper.orderMapper(currentUsername, orderId, currentDate, totalPrice)
            orderDao.insertNewOrder(newOrder)
            return@withContext newOrder
        }

    override suspend fun bookTable(table: TableBooking): TableBooking {
        TODO("Not yet implemented")
    }

    override suspend fun insertStockData(menuItems: List<MenuItem>) {
        menuDao.insertAll(menuItems)
    }

    override suspend fun getTableBookings(table: TableBooking) {
        TODO("Not yet implemented")
    }

    override suspend fun updateStock() {
        TODO("Not yet implemented")
    }

    override fun getCurrentUsername(): String {
        return currentUsername
    }

    override fun setCurrentUsername(username: String) {
        currentUsername = username
    }

    override suspend fun deleteAllItemsInDb() = withContext(Dispatchers.IO) {
        userDao.deleteAllItemsInMenu()
        userDao.deleteAllItemsInOrder()
        userDao.deleteAllItemsInOrderItem()
        userDao.deleteAllItemsInUser()
    }

    override suspend fun getAllOrders(): Flow<List<Order>> {
        return orderDao.getAllOrders()
    }

    override suspend fun getAllMenuItems(): Flow<List<MenuItem>> {
        return menuDao.getAllMenuItems()
    }
}