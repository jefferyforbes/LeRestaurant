package com.jeffery.lerestaurant.ui.screens.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeffery.lerestaurant.data.LeRestaurantService
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.repositories.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val leRestaurantService: LeRestaurantService,
    private val menuRepository: MenuRepository
) : ViewModel() {

    interface MenuListUiState {
        val isLoading: Boolean
        val error: String?
        val itemList: List<MenuItem>
        val selectedItem: MenuItem?

        data class MenuUiState(
            override val isLoading: Boolean = false,
            override val error: String? = null,
            override val itemList: List<MenuItem>,
            override val selectedItem: MenuItem?
        ) : MenuListUiState
    }

    data class MenuListViewModelState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val itemList: List<MenuItem> = emptyList(),
        val selectedItem: MenuItem? = null
    ) {
        fun updateUiState(): MenuListUiState =
            when {
                itemList.isNullOrEmpty() -> {
                    MenuListUiState.MenuUiState(
                        isLoading = isLoading,
                        error = error,
                        itemList = itemList,
                        selectedItem = selectedItem
                    )
                }
                !itemList.isNullOrEmpty() -> {
                    MenuListUiState.MenuUiState(
                        isLoading = isLoading,
                        error = error,
                        itemList = itemList,
                        selectedItem = null
                    )
                }
                selectedItem != null -> {
                    MenuListUiState.MenuUiState(
                        isLoading = isLoading,
                        error = error,
                        itemList = itemList,
                        selectedItem = itemList.find { it.menuItemId == selectedItem.menuItemId }!!
                    )
                }
                else -> {
                    MenuListUiState.MenuUiState(
                        isLoading = isLoading,
                        error = error,
                        itemList = emptyList(),
                        selectedItem = selectedItem
                    )
                }
            }
    }

    private val menuState: MutableStateFlow<MenuListViewModelState> =
        MutableStateFlow(MenuListViewModelState(isLoading = true))

    private val currentItem: MutableStateFlow<MenuItem?> = MutableStateFlow(null)

    val state = menuState
        .map { it.updateUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            menuState.value.updateUiState()
        )

    init {
        viewModelScope.launch {
            delay(850L)
            menuRepository.observeMenuItems().collect { items ->
                Log.d("Menu items: ", "$items")
                menuState.update { it.copy(itemList = items, isLoading = false) }
            }
        }
    }

    fun refresh() {
        Log.d("MenuScreen: ", "Refresh called")
        menuState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                leRestaurantService.getAllMenuItems().collect { items ->
                    Log.d("Menu items: ", "$items")
                    menuState.update { it.copy(itemList = items) }
                }
            } catch (e: Exception) {
                menuState.update { it.copy(error = e.toString()) }
            }
        }
    }

    fun interactWithMenuItem(itemId: Int) {
        selectItem(itemId)
    }

    fun incrementMenuItem() {
        currentItem.getAndUpdate { it }!!.itemCount++
        CoroutineScope(Dispatchers.IO).launch {
            menuRepository.updateMenuItem(currentItem.first()!!)
        }
    }

    fun decrementMenuItem() {
        currentItem.getAndUpdate { it }!!.itemCount--
        CoroutineScope(Dispatchers.IO).launch {
            menuRepository.updateMenuItem(currentItem.first()!!)
        }
    }

    fun resetItemCount() {
        currentItem.getAndUpdate { it }!!.itemCount = 0
        CoroutineScope(Dispatchers.IO).launch {
            menuRepository.updateMenuItem(currentItem.first()!!)
        }
    }

    private fun selectItem(menuItemId: Int) {
        menuState.update { modelState ->
            val item = modelState.itemList.find { it.menuItemId == menuItemId }
            currentItem.getAndUpdate { item }
            modelState.copy(selectedItem = item)
        }
    }
}