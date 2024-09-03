package com.zero.android.ui.theme

import androidx.compose.ui.graphics.Color

private val DarkExtendedColorPalette =
	ExtendedColor(
		chatBubblePrimary = Color(0xFF0A0A0A),
		chatBubblePrimaryVariant = Color(0xFF191919),
		chatBubbleSecondary = CetaceanBlue,
		header = ChineseBlack,
		headerVariant = RaisinBlack,
		buttonPrimary = RaisinBlack75,
		buttonSecondary = Gray,
		colorTextPrimary = Color(0xFFFDFDFF),
		colorTextSecondary = Color(0x92F7F5FF),
		colorTextSecondaryVariant = Color(0x72EEECFF),
		success = EmeraldGreen,
		error = Red300,
		warning = WarningOrange,
		glow = LavenderIndigo,
		glowVariant = EmeraldGreen,
		divider = Gray,
		surface = White,
		surfaceVariant = RaisinBlack,
		surfaceInverse = Black
	)

private val LightExtendedColorPalette =
	ExtendedColor(
		chatBubblePrimary = Color(0xFFF0F0F0),
		chatBubblePrimaryVariant = Color(0xFFF0F0F0),
		chatBubbleSecondary = CetaceanBlue,
		header = Platinum,
		headerVariant = RaisinBlack,
		buttonPrimary = RaisinBlack75,
		buttonSecondary = Gray,
		colorTextPrimary = Black,
		colorTextSecondary = Gray,
		colorTextSecondaryVariant = Gray,
		success = EmeraldGreen,
		error = Red300,
		warning = WarningOrange,
		glow = LavenderIndigo,
		glowVariant = LavenderIndigo,
		divider = Gray,
		surface = Black,
		surfaceVariant = Color(0xFFF0F0F0),
		surfaceInverse = White
	)

// LIGHT THEME PALETTES
private val NeonCyanThemePaletteLight =
	LightExtendedColorPalette.copy(glow = Crayola, glowVariant = Crayola)

private val NeonPinkThemePaletteLight =
	LightExtendedColorPalette.copy(glow = Fuchsia, glowVariant = Fuchsia)

private val NeonRedThemePaletteLight =
	LightExtendedColorPalette.copy(glow = DeepCarminePink, glowVariant = DeepCarminePink)

private val NeonBlueThemePaletteLight =
	LightExtendedColorPalette.copy(glow = VividSkyBlue, glowVariant = VividSkyBlue)

// DARK THEME PALETTES
private val NeonCyanThemePaletteDark =
	DarkExtendedColorPalette.copy(glow = Crayola, glowVariant = DeepGreen)

private val NeonPinkThemePaletteDark =
	DarkExtendedColorPalette.copy(glow = Fuchsia, glowVariant = RoseViolet)

private val NeonRedThemePaletteDark =
	DarkExtendedColorPalette.copy(glow = DeepCarminePink, glowVariant = BloodRed)

private val NeonBlueThemePaletteDark =
	DarkExtendedColorPalette.copy(glow = VividSkyBlue, glowVariant = RichBlack)

private val MeowThemePaletteDark =
	DarkExtendedColorPalette.copy(glow = Blue11, glowVariant = VividSkyBlue)

val DEFAULT_LIGHT_PALETTE = LightExtendedColorPalette
val DEFAULT_DARK_PALETTE = MeowThemePaletteDark

private val lightThemePalettes =
	listOf(
		DEFAULT_LIGHT_PALETTE,
		NeonCyanThemePaletteLight,
		NeonPinkThemePaletteLight,
		NeonRedThemePaletteLight,
		NeonBlueThemePaletteLight
	)

private val darkThemePalettes =
	listOf(
		DEFAULT_DARK_PALETTE,
		NeonCyanThemePaletteDark,
		NeonPinkThemePaletteDark,
		NeonRedThemePaletteDark,
		NeonBlueThemePaletteDark,
		MeowThemePaletteDark
	)

fun getThemePalette(darkMode: Boolean, randomThemePalette: Int): ExtendedColor {
	return if (darkMode) darkThemePalettes.getOrNull(randomThemePalette) ?: DEFAULT_DARK_PALETTE
	else lightThemePalettes.getOrNull(randomThemePalette) ?: DEFAULT_LIGHT_PALETTE
}
