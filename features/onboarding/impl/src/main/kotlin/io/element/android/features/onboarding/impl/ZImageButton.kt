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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZImageButton(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    if (enabled) {
        Card(
            modifier = modifier.wrapContentWidth(),
            onClick = onClick,
            shape = RoundedCornerShape(75.dp)
        ) {
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(id = image),
                contentDescription = null
            )
        }
    } else {
        Text(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.primaryParagraphBold(),
            color = Color.DarkGray
        )
    }
}

@Preview
@Composable
fun ZImageButtonPreview() = ZImageButton(image = R.drawable.img_btn_connect_wallet, text = "")
