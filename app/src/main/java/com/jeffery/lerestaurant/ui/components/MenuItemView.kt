package com.jeffery.lerestaurant.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeffery.lerestaurant.R
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.ui.screens.menu.MenuViewModel
import com.jeffery.lerestaurant.ui.theme.AdditionalShapes
import com.jeffery.lerestaurant.ui.theme.typography
import com.jeffery.lerestaurant.util.Mapper

@Composable
fun MenuItemView(
    modifier: Modifier,
    menuItem: MenuItem,
    context: Context,
    viewModel: MenuViewModel
) {
    val shapes = AdditionalShapes()
    var itemCount by rememberSaveable {
        mutableStateOf(menuItem.itemCount)
    }

    Card(
        shape = shapes.orderCardShape,
        modifier = modifier
            .height(170.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
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
                modifier = modifier.padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 4.dp)
            ) {
                Text(
                    text = menuItem.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 4.dp)
                        .weight(7f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    textAlign = TextAlign.End,
                    text = itemCount.toString(),
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth()
            ) {
                // There is some basic logic to limit the amount of an item the user can request,
                // this is also to help prevent breaking the UI

                IconButton(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    onClick = {
                        if (itemCount > 0) {
                            itemCount--
                            viewModel.removeItemToCurrentOrder(menuItem, itemCount)
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
                    onClick = {
                        itemCount = 0
                        viewModel.removeAllItems()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove all item button"
                    )
                }
                IconButton(
                    modifier = modifier.weight(1f),
                    onClick = {
                        if (itemCount < 5) {
                            itemCount++
                            val orderItem = Mapper.orderItemMapper(menuItem, itemCount)
                            viewModel.addItemToCurrentOrder(orderItem)
                        } else {
                            Toast.makeText(
                                context,
                                "Max count is 5 per item.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add item button")
                }
            }
        }
    }
}