package com.jeffery.lerestaurant.util

import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.entities.Order
import com.jeffery.lerestaurant.data.entities.OrderItem
import com.jeffery.lerestaurant.domain.OrderState
import kotlin.random.Random

object Mapper {
    fun orderMapper(
        username: String,
        orderId: Long,
        orderDate: String,
        totalPrice: String
    ): Order {
        return Order(
            username = username,
            orderId = orderId,
            orderDate = orderDate,
            totalPrice = totalPrice.toDouble(),
            state = OrderState.CONFIRMED.name
        )
    }

    fun orderItemMapper(menuItem: MenuItem, count: Int): OrderItem {
        val orderId = Random.nextLong(1, 999999)
        return OrderItem(
            course = menuItem.course,
            orderId = orderId,
            name = menuItem.name,
            count = count,
            price = menuItem.price
        )
    }
}