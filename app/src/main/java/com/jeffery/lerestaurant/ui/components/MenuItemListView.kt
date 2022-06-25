package com.jeffery.lerestaurant.ui.components

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.domain.CourseType
import com.jeffery.lerestaurant.ui.screens.menu.MenuViewModel
import com.jeffery.lerestaurant.ui.theme.typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuItemListView(
    modifier: Modifier,
    context: Context,
    viewModel: MenuViewModel,
    menuList: List<MenuItem>,
    menuType: CourseType
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val coroutineScope = rememberCoroutineScope()
    val groupedList = menuList.filter { it.course == menuType.name }
    val courseList = listOf(
        CourseType.STARTER,
        CourseType.MAIN,
        CourseType.SIDES,
        CourseType.DRINKS
    )

    BottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        sheetContent = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LazyColumn {
                    items(courseList) { course ->
                        Button(onClick = {
                            viewModel.updateCourseType(course)
                            coroutineScope.launch {
                                sheetState.collapse()
                            }
                        }, modifier = modifier.padding(4.dp)) {
                            Text(text = course.name)
                        }
                    }
                }
            }
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 20.dp,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = menuType.name, fontSize = typography.h6.fontSize) },
                onClick = {
                    coroutineScope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                })
        }
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(end = 10.dp)
        ) {
            LazyColumn {
                items(menuList) { menuItem ->
                    MenuItemView(
                        modifier = modifier,
                        menuItem = menuItem,
                        context,
                        viewModel,
                        { menuItem.menuItemId?.let { viewModel.interactWithMenuItem(it) } },
                        { viewModel.refresh() }
                    )
                }
            }
        }
    }
}