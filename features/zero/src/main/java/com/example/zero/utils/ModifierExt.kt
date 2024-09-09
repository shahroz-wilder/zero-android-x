/*
 * Copyright (c) 2024 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.zero.utils

import android.graphics.BlurMaskFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.bodyPaddings(vertical: Float = 1f, horizontal: Float = 1f) =
    padding(
        vertical = (BODY_PADDING_VERTICAL * vertical).dp,
        horizontal = (BODY_PADDING_HORIZONTAL * horizontal).dp
    )

@Suppress("FunctionName")
fun BodyPadding(vertical: Float = 1f, horizontal: Float = 1f) =
    PaddingValues(
        vertical = (BODY_PADDING_VERTICAL * vertical).dp,
        horizontal = (BODY_PADDING_HORIZONTAL * horizontal).dp
    )

fun Modifier.rightSwipeGesture(onGestureDetected: () -> Unit) =
    this.pointerInput(Unit) {
        detectHorizontalDragGestures { change, dragAmount ->
            change.consume()
            if (dragAmount > 0) {
                onGestureDetected()
            }
        }
    }

fun Modifier.shimmer(shape: Shape = RoundedCornerShape(4.dp)): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val translateAnimation =
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec =
            infiniteRepeatable(
                tween(durationMillis = 1000, easing = FastOutLinearInEasing),
                RepeatMode.Restart
            )
        )
    val brush =
        Brush.linearGradient(
            colors =
            listOf(
                Color.LightGray.copy(alpha = 0.3f),
                Color.LightGray.copy(alpha = 0.1f),
                Color.LightGray.copy(alpha = 0.3f)
            ),
            start = Offset(200f, 200f),
            end = Offset(x = translateAnimation.value, y = translateAnimation.value),
            tileMode = TileMode.Repeated
        )
    return@composed this.then(background(brush, shape))
}

fun Modifier.innerShadow(
    color: Color = Color.Black,
    cornersRadius: Dp = 0.dp,
    spread: Dp = 0.dp,
    blur: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = drawWithContent {
    drawContent()

    val rect = Rect(Offset.Zero, size)
    val paint = Paint()

    drawIntoCanvas {
        paint.color = color
        paint.isAntiAlias = true
        it.saveLayer(rect, paint)
        it.drawRoundRect(
            left = rect.left,
            top = rect.top,
            right = rect.right,
            bottom = rect.bottom,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        if (blur.toPx() > 0) {
            frameworkPaint.maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
        val left =
            if (offsetX > 0.dp) {
                rect.left + offsetX.toPx()
            } else {
                rect.left
            }
        val top =
            if (offsetY > 0.dp) {
                rect.top + offsetY.toPx()
            } else {
                rect.top
            }
        val right =
            if (offsetX < 0.dp) {
                rect.right + offsetX.toPx()
            } else {
                rect.right
            }
        val bottom =
            if (offsetY < 0.dp) {
                rect.bottom + offsetY.toPx()
            } else {
                rect.bottom
            }
        paint.color = Color.Black
        it.drawRoundRect(
            left = left + spread.toPx() / 2,
            top = top + spread.toPx() / 2,
            right = right - spread.toPx() / 2,
            bottom = bottom - spread.toPx() / 2,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
        frameworkPaint.xfermode = null
        frameworkPaint.maskFilter = null
    }
}
