package me.amirkzm.smsadblocker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.amirkzm.smsadblocker.R

val IranSans = FontFamily(
    Font(R.font.iransans_web_medium),
    Font(R.font.iransans_web_bold),
)

val Typography = Typography(
    displaySmall = TextStyle(
        fontFamily = IranSans,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    displayMedium = TextStyle(
        fontFamily = IranSans,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    displayLarge = TextStyle(
        fontFamily = IranSans,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = IranSans,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = IranSans,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = IranSans,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = IranSans,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp
    ),
    titleMedium = TextStyle(
        fontFamily = IranSans,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    titleLarge = TextStyle(
        fontFamily = IranSans,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    bodySmall = TextStyle(
        fontFamily = IranSans,
        fontSize = 10.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = IranSans,
        fontSize = 12.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = IranSans,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = IranSans,
        fontSize = 9.sp
    ),
    labelMedium = TextStyle(
        fontFamily = IranSans,
        fontSize = 11.sp
    ),
    labelLarge = TextStyle(
        fontFamily = IranSans,
        fontSize = 13.sp
    ),
)