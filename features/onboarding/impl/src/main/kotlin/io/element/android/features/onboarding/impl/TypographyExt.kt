package io.element.android.features.onboarding.impl

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.zero.android.ui.theme.interFontTextStyle
import com.zero.android.ui.theme.robotoFontTextStyle

/** Default Font Style Extensions */
fun Typography.defaultPrimary(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) = primaryLabel(fontStyle = fontStyle, textDecoration = textDecoration, fontWeight = fontWeight)

fun Typography.defaultSecondary(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) = secondaryLabel(fontStyle = fontStyle, textDecoration = textDecoration, fontWeight = fontWeight)

/** Primary Font Style Extensions */
fun Typography.primaryHeadLine(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 48.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.primaryHeadLineBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 48.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.primaryTitleLarge(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 36.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.primaryTitleLargeBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 36.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.primaryTitleMedium(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 24.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.primaryTitleMediumBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 24.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.primaryTitle(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 20.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.primaryTitleBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 20.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.primaryTitleSmall(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 18.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.primaryTitleSmallBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 18.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.primaryParagraph(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 16.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.primaryParagraphBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 16.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.primaryLabelLarge(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 14.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.primaryLabelLargeBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 14.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.primaryLabel(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 12.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.primaryLabelBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 12.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.primaryLabelSmall(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 10.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.primaryLabelSmallBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	interFontTextStyle(
		fontSize = 10.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

/** Secondary Font Style Extensions */
fun Typography.secondaryTitleLarge(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 36.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.secondaryTitleLargeBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 36.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.secondaryTitleMedium(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 24.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.secondaryTitleMediumBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 24.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.secondaryTitle(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 20.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.secondaryTitleBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 20.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.secondaryTitleSmall(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 18.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.secondaryTitleSmallBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 18.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.secondaryLabelExtraLarge(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 16.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.secondaryLabelExtraLargeBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 16.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.secondaryLabelLarge(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 14.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.secondaryLabelLargeBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 14.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.secondaryLabel(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 12.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.secondaryLabelBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 12.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)

fun Typography.secondaryLabelSmall(
	fontStyle: FontStyle = FontStyle.Normal,
	fontWeight: FontWeight = FontWeight.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 10.sp,
		fontStyle = fontStyle,
		textDecoration = textDecoration,
		fontWeight = fontWeight
	)

fun Typography.secondaryLabelSmallBold(
	fontStyle: FontStyle = FontStyle.Normal,
	textDecoration: TextDecoration = TextDecoration.None
) =
	robotoFontTextStyle(
		fontSize = 10.sp,
		fontWeight = FontWeight.SemiBold,
		fontStyle = fontStyle,
		textDecoration = textDecoration
	)
