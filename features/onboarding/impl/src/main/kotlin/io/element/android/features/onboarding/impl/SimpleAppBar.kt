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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zero.android.ui.theme.AppTheme
import io.element.android.libraries.designsystem.components.button.BackButton

@Composable
fun SimpleAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    showBackButton: Boolean = true,
    showDivider: Boolean = false,
    onBackPress: () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            if (showBackButton) {
                BackButton(
                    modifier = Modifier.align(Alignment.CenterStart).padding(PADDING_2X.dp),
                    onClick = onBackPress
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = title,
                style = MaterialTheme.typography.primaryParagraph(),
                color = AppTheme.colors.colorTextPrimary
            )
        }
        if (showDivider) {
            Divider(thickness = 0.20.dp, color = Color.White.copy(alpha = 0.1f))
        }
    }
}

@Preview
@Composable
fun SimpleAppBarPreview() = SimpleAppBar(title = "My Simple App bar")
