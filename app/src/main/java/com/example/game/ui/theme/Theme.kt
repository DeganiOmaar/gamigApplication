package com.example.game.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

//  Palette de couleurs pour le mode sombre
private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = iconDarkColor,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = DarkText,
    onBackground = DarkText,
    onSurface = DarkText
)

//  Palette de couleurs pour le mode clair
private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = iconLightColor,
    background = LightBackground,
    surface = LightSurface,
    onPrimary = LightText,
    onBackground = LightText,
    onSurface = LightText
)

@Composable
fun GameTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightStatusBars = !darkTheme
        window.statusBarColor = colorScheme.primary.toArgb()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}