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

package io.element.android.features.onboarding.impl

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zero.android.ui.theme.AppTheme

@Composable
fun ElevatedButton(
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    drawableStart: ImageVector? = null,
    drawableEnd: ImageVector? = null,
    elevation: Dp = 12.dp,
    defaultContentColor: Color = AppTheme.colors.glow,
    onClick: () -> Unit = {}
) {
    val contentColor = if (enabled) defaultContentColor else Color.DarkGray
    val boxModifier =
        if (enabled) {
            modifier.clickable { onClick() }
        } else modifier

    if (enabled) {
        Card(
            modifier = boxModifier.graphicsLayer { this.shadowElevation = elevation.toPx() },
            shape = RoundedCornerShape(75.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = elevation)
        ) {
            Box(
                modifier = boxModifier.width(IntrinsicSize.Max),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.bg_img_button),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                Row(
                    modifier = Modifier.wrapContentWidth().padding(horizontal = PADDING_5X.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    drawableStart?.let {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = it,
                            contentDescription = null,
                            tint = contentColor
                        )
                        if (text.isNotBlank()) {
                            Spacer(modifier = Modifier.size(SPACING_2X.dp))
                        }
                    }
                    if (text.isNotBlank()) {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.primaryParagraphBold(),
                            color = contentColor
                        )
                    }
                    drawableEnd?.let {
                        if (text.isNotBlank()) {
                            Spacer(modifier = Modifier.size(SPACING_2X.dp))
                        }
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = it,
                            contentDescription = null,
                            tint = contentColor
                        )
                    }
                }
            }
        }
    } else {
        Text(text = text,
            style = MaterialTheme.typography.primaryParagraphBold(),
            color = contentColor)
    }
}

@Preview
@Composable
fun ElevatedButtonPreview() =
    ElevatedButton(text = "Button", drawableEnd = Icons.Filled.ArrowForward)
