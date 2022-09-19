package me.amirkzm.smsadblocker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = sunshadeYellowLight,
    primaryVariant = sunshadeYellow,
    secondary = nileBlue,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    background = nileBlueBackground,
)

private val LightColorPalette = lightColors(
    primary = sunshadeYellow,
    primaryVariant = sunshadeYellowDark,
    secondary = nileBlue,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    background = Color.White,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SMSAdBlockerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
    ) {
        val primaryColor = MaterialTheme.colors.primary
        val backgroundColor = MaterialTheme.colors.background
        SideEffect {
            systemUiController.setStatusBarColor(
                color = primaryColor
            )
            systemUiController.setNavigationBarColor(
                color = backgroundColor
            )
        }

        content()
    }
}