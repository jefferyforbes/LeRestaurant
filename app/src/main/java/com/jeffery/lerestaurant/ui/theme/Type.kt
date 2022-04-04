package com.jeffery.lerestaurant.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jeffery.lerestaurant.R

private val Palatino = FontFamily(
    Font(R.font.palatino_regular, FontWeight.Thin),
    Font(R.font.palatino_regular, FontWeight.Normal),
    Font(R.font.palatino_bold, FontWeight.Medium),
    Font(R.font.palatino_bolditalic, FontWeight.Bold)
)


val typography = Typography(
    h1 = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    h2 = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
        fontStyle = FontStyle.Normal
    ),
    h3 = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp,
        fontStyle = FontStyle.Italic,

    ),
    h4 = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.W400,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.Light,
        fontSize = 28.sp
    ),
    h6 = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.W200,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    body2 = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.Thin,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    button = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    caption = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
)