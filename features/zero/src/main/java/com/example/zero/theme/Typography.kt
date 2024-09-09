package com.example.zero.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.zero.R

/** PRIMARY FONT */
private val _interFontFamily =
	FontFamily(
		Font(R.font.inter_regular),
		Font(R.font.inter_bold, FontWeight.Bold),
		Font(R.font.inter_extra_bold, FontWeight.ExtraBold),
		Font(R.font.inter_extra_light, FontWeight.ExtraLight),
		Font(R.font.inter_light, FontWeight.Light),
		Font(R.font.inter_medium, FontWeight.Medium),
		Font(R.font.inter_semi_bold, FontWeight.SemiBold),
		Font(R.font.inter_thin, FontWeight.Thin)
	)

/** SECONDARY FONT */
private val _robotoMonoFontFamily =
	FontFamily(
		Font(R.font.roboto_mono_regular),
		Font(R.font.roboto_mono_bold, FontWeight.Bold),
		Font(R.font.roboto_mono_extra_light, FontWeight.ExtraLight),
		Font(R.font.roboto_mono_light, FontWeight.Light),
		Font(R.font.roboto_mono_medium, FontWeight.Medium),
		Font(R.font.roboto_mono_semi_bold, FontWeight.SemiBold),
		Font(R.font.roboto_mono_thin, FontWeight.Thin)
	)

@Deprecated("Replace the usages with [InterFontStyle] or [RobotoFontStyle] where required")
private val CustomFontStyle = TextStyle(fontFamily = _interFontFamily)

private val _interFontStyle = TextStyle(fontFamily = _interFontFamily, lineHeight = 20.sp)

private val _robotoFontStyle = TextStyle(fontFamily = _robotoMonoFontFamily, lineHeight = 20.sp)

@Deprecated(
	"Replace the usages with Typography.interFontTextStyle() or Typography.robotoFontTextStyle() where required"
)
fun Typography.customTextStyle(
	fontSize: TextUnit,
	fontWeight: FontWeight = FontWeight.Normal,
	fontStyle: FontStyle = FontStyle.Normal
) = CustomFontStyle.copy(fontSize = fontSize, fontWeight = fontWeight, fontStyle = fontStyle)

fun Typography.interFontTextStyle(
	fontSize: TextUnit,
	fontWeight: FontWeight = FontWeight.Normal,
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	_interFontStyle.copy(
		fontSize = fontSize,
		fontWeight = fontWeight,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.robotoFontTextStyle(
	fontSize: TextUnit,
	fontWeight: FontWeight = FontWeight.Normal,
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	_robotoFontStyle.copy(
		fontSize = fontSize,
		fontWeight = fontWeight,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)
