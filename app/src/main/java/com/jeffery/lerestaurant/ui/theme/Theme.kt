package com.jeffery.lerestaurant.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColourPalette = lightColors(
    primary = black,
    primaryVariant = navy,
    secondary = white,
    onSecondary = orange,
    background = white_variation,
    surface = light_blue
)

private val DarkColourPalette = darkColors(
    primary = white,
    primaryVariant = white,
    secondary = black_variation,
    onSecondary = orange,
    background = black,
    surface = light_blue_variation
)

@Composable
fun LeRestaurantTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColourPalette
    } else {
        LightColourPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}