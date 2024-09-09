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

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.CoroutineContext
import kotlin.experimental.ExperimentalTypeInference

inline fun <T> withSameScope(crossinline block: suspend () -> T) =
    CoroutineScope(Dispatchers.Unconfined).launch { block() }

inline fun <T> withScope(dispatcher: CoroutineDispatcher, crossinline block: suspend () -> T) =
    CoroutineScope(dispatcher).launch { block() }

inline fun <T> withScopeAsync(dispatcher: CoroutineDispatcher, crossinline block: suspend () -> T) =
    CoroutineScope(dispatcher).async { block() }

suspend inline fun <T> runOnMainThread(crossinline block: suspend () -> T) =
    withContext(Dispatchers.Main) { block() }

fun <T> FlowCollector<T>.emitInScope(
    value: T,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)
) = scope.launch { emit(value) }

@OptIn(ExperimentalTypeInference::class)
inline fun <T> callbackFlowWithAwait(
    @BuilderInference crossinline block: suspend ProducerScope<T>.() -> Unit
) = callbackFlow {
    block(this)
    awaitClose()
}

@OptIn(ExperimentalTypeInference::class)
inline fun <T> channelFlowWithAwait(
    @BuilderInference crossinline block: suspend ProducerScope<T>.() -> Unit
) = callbackFlow {
    block(this)
    awaitClose()
}

fun <T> runBlockingWithTimeOut(
    context: CoroutineContext = Dispatchers.IO,
    timeoutMillis: Long = 5_000L,
    block: suspend CoroutineScope.() -> T
) = runBlocking(context) { withTimeoutOrNull(timeoutMillis) { block() } }
