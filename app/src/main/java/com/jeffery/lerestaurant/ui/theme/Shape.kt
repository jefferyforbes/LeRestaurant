package com.jeffery.lerestaurant.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(12.dp),
)

class AdditionalShapes {
    val buttonShape = RoundedCornerShape(18.dp)
    val smallButtonShape = RoundedCornerShape(16.dp)
    val orderCardShape = RoundedCornerShape(14.dp)
}

