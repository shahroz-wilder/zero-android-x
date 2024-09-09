package com.example.zero.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColor(
	val chatBubblePrimary: Color,
	val chatBubblePrimaryVariant: Color,
	val chatBubbleSecondary: Color,
	val header: Color,
	val headerVariant: Color,
	val buttonPrimary: Color,
	val buttonSecondary: Color,
	val colorTextPrimary: Color,
	val colorTextSecondary: Color,
	val colorTextSecondaryVariant: Color,
	val success: Color,
	val error: Color,
	val warning: Color,
	val glow: Color,
	val glowVariant: Color,
	val divider: Color,
	val surface: Color,
	val surfaceVariant: Color,
	val surfaceInverse: Color
)

val LocalExtendedColors = staticCompositionLocalOf {
	ExtendedColor(
		chatBubblePrimary = Color.Unspecified,
		chatBubblePrimaryVariant = Color.Unspecified,
		chatBubbleSecondary = Color.Unspecified,
		header = Color.Unspecified,
		headerVariant = Color.Unspecified,
		buttonPrimary = Color.Unspecified,
		buttonSecondary = Color.Unspecified,
		colorTextPrimary = Color.Unspecified,
		colorTextSecondary = Color.Unspecified,
		colorTextSecondaryVariant = Color.Unspecified,
		success = Color.Unspecified,
		error = Color.Unspecified,
		warning = Color.Unspecified,
		glow = Color.Unspecified,
		glowVariant = Color.Unspecified,
		divider = Color.Unspecified,
		surface = Color.Unspecified,
		surfaceVariant = Color.Unspecified,
		surfaceInverse = Color.Unspecified
	)
}
