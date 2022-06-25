package com.jeffery.lerestaurant.ui.screens.currentorder

import android.util.Log
import androidx.lifecycle.*
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.entities.OrderItem
import com.jeffery.lerestaurant.data.repositories.CurrentOrderRepository
import com.jeffery.lerestaurant.data.repositories.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentOrderViewModel @Inject constructor(
    private val currentOrderRepository: CurrentOrderRepository,
    private val menuRepository: MenuRepository
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
        currentOrderState.update {
            it.copy(
                items = listOf(
                    MenuItem(
                        course = "test",
                        name = "name",
                        itemCount = 1,
                        price = 0.0,
                        stock = 1
                    )
                )
            )
        }
    }

    fun refresh() {
        getCurrentOrders()
    }

    private fun getCurrentOrders() {
        viewModelScope.launch {
            menuRepository.observeMenuItems().collect { list ->
                val itemOrder = list.filter { it.itemCount > 0 }
                currentOrderState.update { it.copy(isLoading = false, items = list) }
            }
        }
        Log.d("Current Test", "Test this")
    }

    fun saveOrder() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("works", "test")
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
//            leRestaurantService.removeCurrentOrder()
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