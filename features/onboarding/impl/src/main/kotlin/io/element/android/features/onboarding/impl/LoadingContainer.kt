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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zero.android.ui.theme.AppTheme
import io.element.android.compound.theme.ElementTheme
import io.element.android.libraries.designsystem.preview.ElementPreview
import io.element.android.libraries.designsystem.theme.badgeNeutralBackgroundColor

@Composable
fun LoadingContainer(
    modifier: Modifier = Modifier,
    loading: Boolean,
    size: Dp = 42.dp,
    content: @Composable () -> Unit
) {
    if (loading) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .defaultMinSize(minHeight = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgress(size = size)
        }
    } else {
        content()
    }
}

@Composable
fun OverlappingLoadingContainer(
    modifier: Modifier = Modifier,
    loading: Boolean,
    size: Dp = 42.dp,
    bgColor: Color = AppTheme.colors.surfaceInverse.copy(alpha = 0.65f),
    content: @Composable () -> Unit
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        content()
        if (loading) {
            Column(
                modifier =
                modifier
                    .fillMaxSize()
                    .clickable {}
                    .defaultMinSize(minHeight = 100.dp)
                    .background(color = bgColor),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgress(size = size)
            }
        }
    }
}

@Preview
@Composable
fun LoadingContainerPreview() = ElementPreview {
    OverlappingLoadingContainer(loading = true) { Text(text = "Testing") }
}
