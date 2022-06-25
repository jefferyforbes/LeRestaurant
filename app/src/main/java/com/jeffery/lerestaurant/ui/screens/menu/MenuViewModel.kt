package com.jeffery.lerestaurant.ui.screens.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeffery.lerestaurant.data.LeRestaurantService
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.repositories.MenuRepository
import com.jeffery.lerestaurant.domain.CourseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    interface MenuListUiState {
        val isLoading: Boolean
        val error: String?
        val itemList: List<MenuItem>
        val selectedItem: MenuItem?
        val selectedCourseType: CourseType

        data class MenuUiState(
            override val isLoading: Boolean = false,
            override val error: String? = null,
            override val itemList: List<MenuItem>,
            override val selectedItem: MenuItem?,
            override val selectedCourseType: CourseType = CourseType.MAIN
        ) : MenuListUiState
    }

    data class MenuListViewModelState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val itemList: List<MenuItem> = emptyList(),
        val selectedItem: MenuItem? = null,
        val isCourseTypeBottomSheetOpen: Boolean = false,
        val selectedCourseType: CourseType = CourseType.MAIN
    ) {
        fun updateUiState(): MenuListUiState =
            when {
                itemList.isNullOrEmpty() -> {
                    MenuListUiState.MenuUiState(
                        isLoading = isLoading,
                        error = error,
                        itemList = itemList,
                        selectedItem = selectedItem,
                        selectedCourseType = CourseType.MAIN
                    )
                }
                !itemList.isNullOrEmpty() -> {
                    MenuListUiState.MenuUiState(
                        isLoading = isLoading,
                        error = error,
                        itemList = itemList,
                        selectedItem = null,
                        selectedCourseType = CourseType.MAIN
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
            delay(800L)
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
                menuRepository.observeMenuItems().collect { items ->
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

    fun interactWithFab() {
        menuState.getAndUpdate { modelState ->
            if (modelState.isCourseTypeBottomSheetOpen) {
                modelState.copy(isCourseTypeBottomSheetOpen = false)
            } else {
                modelState.copy(isCourseTypeBottomSheetOpen = true)
            }
        }
    }

    fun updateCourseType(updatedCourseType: CourseType) {
        menuState.update { modelState ->
            modelState.copy(selectedCourseType = updatedCourseType)
        }
    }

    private fun selectItem(menuItemId: Int) {
        menuState.update { modelState ->
            val item = modelState.itemList.find { it.menuItemId == menuItemId }
            currentItem.getAndUpdate { item }
            modelState.copy(selectedItem = item)
        }
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
}