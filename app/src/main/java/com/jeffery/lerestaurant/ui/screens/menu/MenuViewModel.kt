package com.jeffery.lerestaurant.ui.screens.menu

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeffery.lerestaurant.data.LeRestaurantService
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.entities.OrderItem
import com.jeffery.lerestaurant.util.Mapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val leRestaurantService: LeRestaurantService
) : ViewModel() {

    private var _allMenuItems: List<MenuItem> = emptyList()
    var allMenuItems: List<MenuItem> = _allMenuItems

    private var _menuState = mutableStateOf<MenuListState>(MenuListState.Loading)
    var menuState: MutableState<MenuListState> = _menuState

    init {
        getMenuItems()
    }

    fun refresh() {
        Log.d("MenuScreen: ", "Refresh called")
        getMenuItems()
    }

    fun addItemToCurrentOrder(orderItem: OrderItem) {
        viewModelScope.launch {
            leRestaurantService.addCurrentOrderItem(orderItem)
        }
    }

    fun removeItemToCurrentOrder(menuItem: MenuItem, count: Int) {
        menuItem.menuItemId?.let {
            leRestaurantService.removeOneOrderItem(Mapper.orderItemMapper(menuItem, count))
        }
        leRestaurantService.removeOneOrderItem(Mapper.orderItemMapper(menuItem, count))
    }

    fun removeAllItems() {
        leRestaurantService.removeCurrentOrder()
    }

    private fun getMenuItems() {
        viewModelScope.launch {
            leRestaurantService.getAllMenuItems().collect {
                allMenuItems = it
                Log.d("Orders: ", "$it")
                if (allMenuItems.isEmpty()) {
                    _menuState.value = MenuListState.Loading
                } else {
                    _menuState.value = MenuListState.Content
                }
                _menuState.value = MenuListState.Idle
            }
        }
    }

    sealed class MenuListState {
        object Loading : MenuListState()
        object Idle : MenuListState()
        object Content : MenuListState()
    }
}