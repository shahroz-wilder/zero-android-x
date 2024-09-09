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

package com.example.zero.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.zero.utils.primaryParagraph
import com.example.zero.theme.AppTheme
import io.element.android.libraries.designsystem.preview.ElementPreview

@Composable
fun SegmentedControl(
    modifier: Modifier = Modifier,
    controlWidth: Dp? = null,
    items: List<String>,
    defaultSelectedItemIndex: Int = 0,
    cornerRadius: Int = 75,
    onItemSelection: (selectedItemIndex: Int) -> Unit
) {
    val selectedIndex = remember { mutableIntStateOf(defaultSelectedItemIndex) }
    val itemIndex = remember { mutableIntStateOf(defaultSelectedItemIndex) }

    val segmentContainerColor = AppTheme.colors.colorTextPrimary.copy(alpha = 0.05f)

    val mModifier =
        if (controlWidth == null) {
            modifier.fillMaxWidth()
        } else modifier.width(controlWidth)
    Row(
        modifier =
        mModifier
            .height(44.dp)
            .background(segmentContainerColor, RoundedCornerShape(cornerRadius)),
        horizontalArrangement = Arrangement.Center
    ) {
        items.forEachIndexed { index, item ->
            itemIndex.value = index
            if (selectedIndex.value == index) {
                SegmentSelectedControl(controlWidth, text = item) {
                    selectedIndex.value = index
                    onItemSelection(selectedIndex.value)
                }
            } else {
                val itemModifier =
                    if (controlWidth == null) {
                        Modifier.weight(1f)
                    } else {
                        val itemWidth = controlWidth.div(2)
                        Modifier.width(itemWidth)
                    }
                Card(
                    modifier = itemModifier.fillMaxHeight(),
                    onClick = {
                        selectedIndex.value = index
                        onItemSelection(selectedIndex.value)
                    },
                    colors =
                    CardDefaults.cardColors(
                        containerColor =
                        if (selectedIndex.value == index) {
                            Color.White
                        } else {
                            Color.Transparent
                        },
                        contentColor = AppTheme.colors.colorTextPrimary
                    ),
                    shape =
                    when (index) {
                        0 ->
                            RoundedCornerShape(
                                topStartPercent = cornerRadius,
                                topEndPercent = cornerRadius,
                                bottomStartPercent = cornerRadius,
                                bottomEndPercent = cornerRadius
                            )
                        items.size - 1 ->
                            RoundedCornerShape(
                                topStartPercent = cornerRadius,
                                topEndPercent = cornerRadius,
                                bottomStartPercent = cornerRadius,
                                bottomEndPercent = cornerRadius
                            )
                        else ->
                            RoundedCornerShape(
                                topStartPercent = 0,
                                topEndPercent = 0,
                                bottomStartPercent = 0,
                                bottomEndPercent = 0
                            )
                    }
                ) {
                    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.primaryParagraph(),
                            textAlign = TextAlign.Center,
                            color = AppTheme.colors.colorTextPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.SegmentSelectedControl(
    controlWidth: Dp? = null,
    text: String,
    onClick: () -> Unit = {}
) {
    val mModifier =
        if (controlWidth == null) {
            Modifier.weight(1f)
        } else {
            val itemWidth = controlWidth.div(2)
            Modifier.width(itemWidth)
        }
    Box(modifier = mModifier) {
        ElevatedButton(
            modifier = mModifier.fillMaxHeight(),
            text = text,
            onClick = onClick,
            defaultContentColor = AppTheme.colors.colorTextPrimary,
            elevation = 6.dp
        )
    }
}

@Preview
@Composable
fun SegmentedControlPreview() =
    ElementPreview {
        SegmentedControl(items = listOf("Item 1", "Item 2"), onItemSelection = {})
    }
