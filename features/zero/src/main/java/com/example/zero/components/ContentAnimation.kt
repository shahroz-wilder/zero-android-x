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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FadeAnimation(
    modifier: Modifier = Modifier,
    visible: Boolean = false,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(modifier = modifier, visible = visible, enter = fadeIn(), exit = fadeOut()) {
        content()
    }
}

@Composable
fun FadeExpandAnimation(
    modifier: Modifier = Modifier,
    visible: Boolean = false,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {
        content()
    }
}

@Composable
fun FadeSlideAnimation(
    modifier: Modifier = Modifier,
    visible: Boolean = false,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = slideInHorizontally() + fadeIn(),
        exit = slideOutHorizontally() + fadeOut()
    ) {
        content()
    }
}

@Composable
fun InstantAnimation(
    modifier: Modifier = Modifier,
    visible: Boolean = false,
    durationMillis: Int = 350,
    enterAnimation: EnterTransition = fadeIn(animationSpec = tween(durationMillis)),
    exitAnimation: ExitTransition = fadeOut(animationSpec = tween(durationMillis)),
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visibleState = MutableTransitionState(visible).apply { targetState = !visible },
        enter = enterAnimation,
        exit = exitAnimation
    ) {
        content()
    }
}

@Composable
fun CustomisedAnimation(
    modifier: Modifier = Modifier,
    visible: Boolean = false,
    durationMillis: Int = 350,
    enterAnimation: EnterTransition = fadeIn(animationSpec = tween(durationMillis)),
    exitAnimation: ExitTransition = fadeOut(animationSpec = tween(durationMillis)),
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = enterAnimation,
        exit = exitAnimation
    ) {
        content()
    }
}
