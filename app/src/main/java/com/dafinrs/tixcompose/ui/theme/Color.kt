package com.dafinrs.tixcompose.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

data class ColorApp(
    val primaryColor: Color,
    val onPrimaryColor: Color,
    val secondary: Color,
    val onSecondary: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val error: Color,
    val onError: Color,
    val background: Color,
    val onBackground: Color,
    val outline: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
) {

    fun darkColor(): ColorScheme = darkColorScheme(
        primary = primaryColor,
        onPrimary = onPrimaryColor,
        secondary = secondary,
        onSecondary = onSecondary,
        tertiary = tertiary,
        onTertiary = onTertiary,
        error = error,
        onError = onError,
        background = background,
        onBackground = onBackground,
        outline = outline,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
    )

    fun light(): ColorScheme = lightColorScheme(
        primary = primaryColor,
        onPrimary = onPrimaryColor,
        secondary = secondary,
        onSecondary = onSecondary,
        tertiary = tertiary,
        onTertiary = onTertiary,
        error = error,
        onError = onError,
        background = background,
        onBackground = onBackground,
        outline = outline,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
    )

    companion object {
        val light = ColorApp(
            primaryColor = Color(0xff2e6c00),
            onPrimaryColor = Color(0xffffffff),
            secondary = Color(0xff56624b),
            onSecondary = Color(0xffffffff),
            tertiary = Color(0xff386666),
            onTertiary = Color(0xff386666),
            error = Color(0xffba1a1a),
            onError = Color(0xffffffff),
            background = Color(0xfffdfdf5),
            onBackground = Color(0xff1a1c18),
            outline = Color(0xff74796d),
            primaryContainer = Color(0xff80ff2c),
            onPrimaryContainer = Color(0xff092100),
            secondaryContainer = Color(0xffd9e7ca),
            onSecondaryContainer = Color(0xff141e0d),
            tertiaryContainer = Color(0xffbbebeb),
            onTertiaryContainer = Color(0xff002020),
            errorContainer = Color(0xffffdad6),
            onErrorContainer = Color(0xff410002),
            surface = Color(0xfffdfdf5),
            onSurface = Color(0xff1a1c18),
            surfaceVariant = Color(0xffe0e4d6),
            onSurfaceVariant = Color(0xff43483e)
        )

        val dark = ColorApp(
            primaryColor = Color(0xff67e100),
            onPrimaryColor = Color(0xff153800),
            secondary = Color(0xffbdcbaf),
            onSecondary = Color(0xff283420),
            tertiary = Color(0xffa0cfcf),
            onTertiary = Color(0xff003737),
            error = Color(0xffffb4ab),
            onError = Color(0xffe3e3dc),
            background = Color(0xff1a1c18),
            onBackground = Color(0xffe3e3dc),
            outline = Color(0xff8e9286),
            primaryContainer = Color(0xff215100),
            onPrimaryContainer = Color(0xff80ff2c),
            secondaryContainer = Color(0xff3e4a35),
            onSecondaryContainer = Color(0xffd9e7ca),
            tertiaryContainer = Color(0xff1e4e4e),
            onTertiaryContainer = Color(0xffbbebeb),
            errorContainer = Color(0xff93000a),
            onErrorContainer = Color(0xffffdad6),
            surface = Color(0xff1a1c18),
            onSurface = Color(0xffe3e3dc),
            surfaceVariant = Color(0xff43483e),
            onSurfaceVariant = Color(0xffc4c8bb)
        )

        val textColorBuyTicketButton = Color(0xffFFAB00)
    }
}
