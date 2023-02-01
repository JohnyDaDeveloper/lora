package cz.johnyapps.lora.feature.core

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun LoraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColors: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = LoraTheme.getColors(
        darkTheme = darkTheme,
        dynamicColors = dynamicColors
    )

    MaterialTheme(
        colorScheme = colors,
        shapes = LoraTheme.shapes,
        typography = LoraTheme.typography,
        content = content
    )

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
}
