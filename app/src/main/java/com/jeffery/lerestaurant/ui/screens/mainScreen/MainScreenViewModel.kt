package com.jeffery.lerestaurant.ui.screens.mainScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeffery.lerestaurant.data.LeRestaurantService
import com.jeffery.lerestaurant.data.entities.Order
import com.jeffery.lerestaurant.data.entities.OrderItem
import com.jeffery.lerestaurant.domain.CourseType
import com.jeffery.lerestaurant.domain.OrderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val leRestaurantService: LeRestaurantService
) : ViewModel() {

    private var _allOrders: List<Order> = emptyList()
    var allOrders: List<Order> = _allOrders

    private var _orderListState = mutableStateOf<OrderListState>(OrderListState.Loading)
    var orderListState: MutableState<OrderListState> = _orderListState

    init {
        getSavedOrders()
    }

    fun refresh() {
        Log.d("MainScreen: ", "Refresh called")
        getSavedOrders()
    }

    private fun getSavedOrders() {
        viewModelScope.launch {
            leRestaurantService.getAllOrders().collect {
                allOrders = it
                if (allOrders.isEmpty()) {
                    OrderListState.Loading
                } else {
                    OrderListState.Content
                }
                orderListState.value = OrderListState.Idle
                Log.d("Orders: ", "$it")
            }
        }
    }

    sealed class OrderListState {
        object Loading : OrderListState()
        object Idle : OrderListState()
        object Content : OrderListState()
    }
}