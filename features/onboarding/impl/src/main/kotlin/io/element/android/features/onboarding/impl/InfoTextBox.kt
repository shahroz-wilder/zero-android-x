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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zero.android.ui.theme.AppTheme

@Composable
fun ErrorTextBox(modifier: Modifier = Modifier, text: String) {
    InfoTextBox(modifier = modifier, type = InfoTextType.ERROR, text = text)
}

@Composable
fun SuccessTextBox(modifier: Modifier = Modifier, text: String) {
    InfoTextBox(modifier = modifier, type = InfoTextType.SUCCESS, text = text)
}

@Composable
fun InfoBox(modifier: Modifier = Modifier, text: String) {
    InfoTextBox(modifier = modifier, type = InfoTextType.OTHER, text = text)
}

@Composable
private fun InfoTextBox(
    modifier: Modifier = Modifier,
    type: InfoTextType = InfoTextType.OTHER,
    text: String
) {
    val contentColor: Color =
        when (type) {
            InfoTextType.SUCCESS -> AppTheme.colors.success
            InfoTextType.ERROR -> AppTheme.colors.error
            else -> AppTheme.colors.colorTextSecondary
        }
    val backgroundColor =
        when (type) {
            InfoTextType.SUCCESS -> Color(0xFF0C1F17)
            InfoTextType.ERROR -> Color(0xFF291415)
            else -> Color(0x10A3A2A3)
        }
    Box(modifier = modifier.background(color = backgroundColor, shape = RoundedCornerShape(6.dp))) {
        Row(
            modifier = Modifier.padding(vertical = PADDING_2X.dp, horizontal = PADDING_4X.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector =
                when (type) {
                    InfoTextType.SUCCESS -> Icons.Default.Check
                    InfoTextType.ERROR -> Icons.Default.Error
                    else -> Icons.Default.Info
                },
                contentDescription = null,
                tint = contentColor
            )
            Spacer(modifier = Modifier.size(SPACING_2X.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.primaryLabelLarge(),
                color = contentColor
            )
        }
    }
}

private enum class InfoTextType {
    SUCCESS,
    ERROR,
    OTHER
}
