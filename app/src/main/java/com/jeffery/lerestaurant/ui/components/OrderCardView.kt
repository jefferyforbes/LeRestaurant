package com.jeffery.lerestaurant.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeffery.lerestaurant.data.entities.Order
import com.jeffery.lerestaurant.ui.theme.Shapes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderCardView(modifier: Modifier, order: Order) {

    var expandedState by rememberSaveable {
        mutableStateOf(false)
    }

    val rotateState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

    val orderId = order.orderId
    val username = order.username
    val date = order.orderDate
    val total = order.totalPrice
    val state = order.state

    Card(
        shape = Shapes.large,
        border = BorderStroke(4.dp, if (isSystemInDarkTheme()) Color.LightGray else Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
            .animateContentSize(
                animationSpec = TweenSpec(
                    durationMillis = 400,
                    easing = FastOutSlowInEasing
                )
            ),
        onClick = { expandedState = !expandedState }
    ) {
        Column(
            modifier
                .wrapContentSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.padding()
            ) {
                Text(
                    text = "Order #$orderId",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 4.dp)
                        .weight(7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = date,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 4.dp)
                        .weight(6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                IconButton(
                    modifier = modifier
                        .alpha(ContentAlpha.medium)
                        .fillMaxWidth()
                        .height(40.dp)
                        .weight(1f)
                        .rotate(rotateState),
                    onClick = { expandedState = !expandedState }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown arrow",
                    )
                }
            }
            if (expandedState) {
                OrderCardItem(key = "Order id: ", value = orderId.toString())
                OrderCardItem(key = "Username: ", value = username)
                OrderCardItem(key = "Date: ", value = date)
                OrderCardItem(key = "Total: ", value = total.toString())
                Spacer(modifier = modifier.padding(4.dp))
                OrderCardItem(key = "State: ", value = state)
            }
        }
    }
}