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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zero.android.ui.theme.AppTheme

@Composable
fun BoxScope.AuthScreenFooter(
    preText: String,
    postText: String,
    verticalPaddings: Dp = DEFAULT_PADDING.dp,
    onClick: () -> Unit = {}
) {
    Text(
        modifier =
        Modifier.align(Alignment.BottomCenter).padding(vertical = verticalPaddings).clickable {
            onClick.invoke()
        },
        text =
        buildAnnotatedString {
            withStyle(SpanStyle(color = AppTheme.colors.colorTextSecondary
            )) { append(preText) }
            append(" ")
            withStyle(
                style =
                SpanStyle(
                    color = AppTheme.colors.glow,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append(postText)
            }
        },
        style = MaterialTheme.typography.primaryLabelLarge(),
        textAlign = TextAlign.Center
    )
}
