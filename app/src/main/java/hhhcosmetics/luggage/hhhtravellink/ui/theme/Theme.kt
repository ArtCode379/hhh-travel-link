package hhhcosmetics.luggage.hhhtravellink.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val BrandColors = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Accent,
    onSecondary = OnPrimary,
    background = Background,
    onBackground = OnSurface,
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = ChipBackground,
    onSurfaceVariant = Muted,
    outline = Border,
    tertiary = Success,
)

@Composable
fun ProductAppQJIOOTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = BrandColors,
        typography = AppTypography,
        content = content,
    )
}
