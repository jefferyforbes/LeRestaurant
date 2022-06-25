package com.jeffery.lerestaurant.ui.components

import android.content.Context
import android.view.animation.BounceInterpolator
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeffery.lerestaurant.R
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.ui.screens.dialog.Dialog
import com.jeffery.lerestaurant.ui.screens.menu.MenuViewModel
import com.jeffery.lerestaurant.ui.theme.AdditionalShapes
import com.jeffery.lerestaurant.ui.theme.typography
import com.jeffery.lerestaurant.util.Mapper

@Composable
fun MenuItemView(
    modifier: Modifier,
    menuItem: MenuItem,
    context: Context,
    viewModel: MenuViewModel,
    updateItem: () -> Unit,
    refreshScreen: () -> Unit
) {
    val shapes = AdditionalShapes()

    val openDialog = rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        shape = shapes.orderCardShape,
        modifier = modifier
            .height(170.dp)
            .fillMaxWidth()
            .padding(10.dp, top = 6.dp)
    ) {
        AnimatedVisibility(
            visible = openDialog.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            menuItem.menuItemId?.let { viewModel.interactWithMenuItem(it) }
            Dialog(
                title = "Add item to current order",
                state = openDialog,
                content = {
                    DialogMenuItem(
                        menuItem,
                        context,
                        { viewModel.incrementMenuItem() },
                        { viewModel.decrementMenuItem() },
                        { viewModel.resetItemCount() }
                    )
                },
                onDismiss = refreshScreen,
                onConfirm = refreshScreen
            )
        }

        Button(onClick = { openDialog.value = true }) {
            Column(
                modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier.padding(4.dp)
                ) {
                    Text(
                        text = menuItem.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(4.dp)
                            .weight(7f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        textAlign = TextAlign.End,
                        text = menuItem.itemCount.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(start = 12.dp)
                            .height(12.dp)
                            .fillMaxWidth()
                            .weight(2f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier.wrapContentSize()
                ) {
                    Text(
                        text = "£${menuItem.price}",
                        fontSize = 20.sp,
                        fontStyle = typography.subtitle1.fontStyle,

                        fontWeight = FontWeight.Light,
                        modifier = modifier
                            .padding(start = 14.dp, top = 4.dp, bottom = 6.dp, end = 4.dp)
                            .weight(7f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun DialogMenuItem(
    menuItem: MenuItem,
    context: Context,
    incrementItem: () -> Unit,
    decrementItem: () -> Unit,
    resetItem: () -> Unit,
) {
    val modifier = Modifier

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        DialogText(label = "Name: ", text = menuItem.name)
        DialogText(label = "Course Type: ", text = menuItem.course)
        DialogText(label = "Price: ", text = menuItem.price.toString())
        DialogText(label = "Item count: ", text = menuItem.itemCount.toString())
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxWidth()
        ) {
            IconButton(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = {
                    if (menuItem.itemCount > 0) {
                        decrementItem()
                    }
                }
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_baseline_remove_24),
                    contentDescription = "Remove one item button"
                )
            }
            IconButton(
                modifier = modifier.weight(1f),
                onClick = { resetItem() }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove all item button"
                )
            }
            IconButton(
                modifier = modifier.weight(1f),
                onClick = {
                    if (menuItem.itemCount < 5) {
                        incrementItem()
                    } else {
                        Toast.makeText(
                            context,
                            "Max count is 5 per item.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add item button"
                )
            }
        }
    }
}

@Composable
fun DialogText(label: String, text: String) {
    val modifier = Modifier
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.wrapContentSize(Alignment.Center)
            .padding( top = 6.dp, bottom = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontStyle = typography.h2.fontStyle,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1,
            modifier = modifier.padding(end = 6.dp)
        )
        Text(
            text = text,
            fontSize = if (text.length > 14) 16.sp else 20.sp,
            fontStyle = typography.body1.fontStyle,
            fontWeight = FontWeight.Light,
        )
    }
}