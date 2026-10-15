package com.aistudio.aulaudio.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = CarbonBlack,
    onPrimary = White,
    primaryContainer = CarbonDark,
    onPrimaryContainer = White,
    secondary = AccentIndigo,
    onSecondary = White,
    background = SurfaceCanvas,
    onBackground = TextPrimary,
    surface = White,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceContainer,
    onSurfaceVariant = TextSecondary,
    outline = SurfaceBorder,
    outlineVariant = SurfaceBorderSubtle,
    error = LiveRed,
    onError = White
)

private val DarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = CarbonBlack,
    primaryContainer = CarbonDark,
    onPrimaryContainer = White,
    secondary = AccentIndigo,
    onSecondary = White,
    background = CarbonDark,
    onBackground = White,
    surface = CarbonBlack,
    onSurface = White,
    surfaceVariant = CarbonDark,
    onSurfaceVariant = TextTertiary,
    outline = SurfaceBorder,
    outlineVariant = SurfaceBorderSubtle,
    error = LiveRed,
    onError = White
)

@Composable
fun AulaAudioTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
