package com.jeffery.lerestaurant.ui.screens.currentOrder

import androidx.lifecycle.*
import com.jeffery.lerestaurant.data.LeRestaurantService
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.entities.OrderItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentOrderViewModel @Inject constructor(
    private val leRestaurantService: LeRestaurantService
) : ViewModel() {

    interface CurrentOrderUiState {
        val isLoading: Boolean
        val error: String?
        val items: List<MenuItem>

        data class UiState(
            override val isLoading: Boolean = false,
            override val error: String? = null,
            override val items: List<MenuItem>
        ) : CurrentOrderUiState
    }

    data class CurrentOrderViewModelState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val items: List<MenuItem> = emptyList()
    ) {
        fun updateUiState(): CurrentOrderUiState = when {
            items.isNullOrEmpty() -> {
                CurrentOrderUiState.UiState(
                    isLoading = false,
                    error = null,
                    items = emptyList()
                )
            }
            !items.isNullOrEmpty() -> {
                CurrentOrderUiState.UiState(
                    isLoading = false,
                    error = null,
                    items = items
                )
            }
            else -> {
                CurrentOrderUiState.UiState(
                    isLoading = false,
                    error = "Something went wrong",
                    items = emptyList()
                )
            }
        }
    }

    private val currentOrderState: MutableStateFlow<CurrentOrderViewModelState> =
        MutableStateFlow(CurrentOrderViewModelState(isLoading = true))

    val state = currentOrderState
        .map { it.updateUiState() }
        .stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        currentOrderState.value.updateUiState()
    )

    //    private var totalPrice: Double = totalPrice()

    init {
        currentOrderState.update { it.copy(isLoading = true) }
        getCurrentOrders()
    }

    fun refresh() {
        getCurrentOrders()
    }

    private fun getCurrentOrders() {
        viewModelScope.launch {
            leRestaurantService.observeCurrentOrder().collect { list ->
                currentOrderState.update { it.copy(isLoading = false, items = list) }
            }
        }
    }

    fun saveOrder() {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }

    fun addOrderItem(orderItem: OrderItem) {
        viewModelScope.launch {
//            if (currentOrderList.size < 6) {
//                leRestaurantService.addMenuItemToCurrentOrderItem(orderItem)
//            } else {
//                currentOrderState.value = CurrentOrderState.MaxCurrentItems
//            }
        }
    }

    fun removeOrderItem(orderItem: OrderItem) {
//        if (!currentOrderList.isNullOrEmpty()) {
//            leRestaurantService.removeOneMenuItemFromCurrentOrder(orderItem)
//        }
//        currentOrderState.value = CurrentOrderState.Loading
    }

    fun removeAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            leRestaurantService.removeCurrentOrder()
        }
    }

//    private fun totalPrice(): Double {
//        val finalTotal = 0.00
//        if (!currentOrderList.isNullOrEmpty()) {
//            currentOrderList.forEach {
//                finalTotal.plus(it.price)
//            }
//        }
//        return finalTotal
//    }
}