package cz.johnyapps.lora.feature.core

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.Shapes
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

object LoraTheme {
    @Composable
    @ReadOnlyComposable
    fun getColors(
        darkTheme: Boolean,
        dynamicColors: Boolean
    ): ColorScheme {
        val context = LocalContext.current

        return if (dynamicColors) {
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        } else {
            if (darkTheme) {
                LoraDarkColors
            } else {
                LoraLightColors
            }
        }
    }

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography
}