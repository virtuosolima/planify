package br.edu.ifpe.planify.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = SurfaceLight,
    primaryContainer = SecondaryBlue,
    onPrimaryContainer = TextLight,
    secondary = TertiaryBlue,
    onSecondary = SurfaceLight,
    secondaryContainer = SecondaryBlue,
    onSecondaryContainer = TextLight,
    tertiary = TertiaryBlue,
    onTertiary = SurfaceLight,
    background = BackgroundLight,
    onBackground = TextLight,
    surface = SurfaceLight,
    onSurface = TextLight,
    error = ErrorRed,
    onError = SurfaceLight
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = BackgroundDark,
    primaryContainer = SecondaryDark,
    onPrimaryContainer = TextDark,
    secondary = TertiaryDark,
    onSecondary = BackgroundDark,
    secondaryContainer = SecondaryDark,
    onSecondaryContainer = TextDark,
    tertiary = TertiaryDark,
    onTertiary = BackgroundDark,
    background = BackgroundDark,
    onBackground = TextDark,
    surface = SurfaceDark,
    onSurface = TextDark,
    error = ErrorRed,
    onError = SurfaceLight
)

@Composable
fun PlanifyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
