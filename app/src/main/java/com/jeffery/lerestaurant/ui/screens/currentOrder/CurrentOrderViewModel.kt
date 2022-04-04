package com.jeffery.lerestaurant.ui.screens.currentOrder

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeffery.lerestaurant.data.LeRestaurantService
import com.jeffery.lerestaurant.data.entities.OrderItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentOrderViewModel @Inject constructor(
    private val leRestaurantService: LeRestaurantService
) : ViewModel() {

    val currentOrderState: MutableState<CurrentOrderState> =
        mutableStateOf(CurrentOrderState.Loading)
    private var totalPrice: Double = totalPrice()
    var currentOrderList: List<OrderItem> = emptyList()

    init {
        getCurrentOrders()
    }

    fun refresh() {
        getCurrentOrders()
    }

    private fun getCurrentOrders() {
        viewModelScope.launch {
            leRestaurantService.getAllCurrentOrders().collect {
                currentOrderList = if (it.isNullOrEmpty()) {
                    currentOrderState.value = CurrentOrderState.Loading
                    listOf(
                        OrderItem(
                            course = "",
                            name = "",
                            count = 0,
                            price = 0.00
                        )
                    )
                } else {
                    it
                }
            }
        }
        currentOrderState.value = CurrentOrderState.Idle
    }

    fun saveOrder() {
        viewModelScope.launch {
            if (!currentOrderList.isNullOrEmpty()) {
                totalPrice()
                leRestaurantService.saveOrder(totalPrice.toString(), currentOrderList)
            } else {
                currentOrderState.value = CurrentOrderState.EmptyFields
            }
        }
        currentOrderState.value = CurrentOrderState.Loading
        currentOrderState.value = CurrentOrderState.Idle
    }

    fun addOrderItem(orderItem: OrderItem) {
        viewModelScope.launch {
            if (currentOrderList.size < 6) {
                leRestaurantService.addCurrentOrderItem(orderItem)
            } else {
                currentOrderState.value = CurrentOrderState.MaxCurrentItems
            }
        }
    }

    fun removeOrderItem(orderItem: OrderItem) {
        if (!currentOrderList.isNullOrEmpty()) {
            leRestaurantService.removeOneOrderItem(orderItem)
        }
        currentOrderState.value = CurrentOrderState.Loading
    }

    fun removeAllItems() {
        leRestaurantService.removeCurrentOrder()
        currentOrderState.value = CurrentOrderState.Loading
    }

    private fun totalPrice(): Double {
        val finalTotal = 0.00
        if (!currentOrderList.isNullOrEmpty()) {
            currentOrderList.forEach {
                finalTotal.plus(it.price)
            }
        }
        return finalTotal
    }

    sealed class CurrentOrderState {
        object Idle : CurrentOrderState()
        object Loading : CurrentOrderState()
        object Content : CurrentOrderState()
        object EmptyFields : CurrentOrderState()
        object MaxCurrentItems : CurrentOrderState()
    }
}