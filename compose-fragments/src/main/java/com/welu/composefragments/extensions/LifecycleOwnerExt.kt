package com.welu.composefragments.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Collects the [Flow] when the [Lifecycle] of the provided [LifecycleOwner] reaches [Lifecycle.State.STARTED]
 */
inline fun <reified T> LifecycleOwner.collectOnStarted(
    flow: Flow<T>,
    firstTimeDelay: Long = 0L,
    noinline collector: suspend (T) -> Unit
) = collectOnLifecycle(
    flow = flow,
    onLifeCycleState = Lifecycle.State.STARTED,
    firstTimeDelay = firstTimeDelay,
    collector = collector
)

/**
 * Collects the [Flow] when the [Lifecycle] of the provided [LifecycleOwner] reaches [Lifecycle.State.RESUMED]
 */
inline fun <reified T> LifecycleOwner.collectOnResumed(
    flow: Flow<T>,
    firstTimeDelay: Long = 0L,
    noinline collector: suspend (T) -> Unit
) = collectOnLifecycle(
    flow = flow,
    onLifeCycleState = Lifecycle.State.RESUMED,
    firstTimeDelay = firstTimeDelay,
    collector = collector
)

/**
 * Collects the [Flow] when the [Lifecycle] of the provided [LifecycleOwner] reaches [Lifecycle.State.CREATED]
 */
inline fun <reified T> LifecycleOwner.collectOnCreated(
    flow: Flow<T>,
    firstTimeDelay: Long = 0L,
    noinline collector: suspend (T) -> Unit
) = collectOnLifecycle(
    flow = flow,
    onLifeCycleState = Lifecycle.State.CREATED,
    firstTimeDelay = firstTimeDelay,
    collector = collector
)

/**
 * Collects the [Flow] when the [Lifecycle] of the provided [LifecycleOwner] reaches a specific [Lifecycle.State]
 */
inline fun <reified T> LifecycleOwner.collectOnLifecycle(
    flow: Flow<T>,
    onLifeCycleState: Lifecycle.State,
    firstTimeDelay: Long = 0L,
    noinline collector: suspend (T) -> Unit
) {
    lifecycleScope.launch(Dispatchers.Main.immediate) {
        delay(firstTimeDelay)
        lifecycle.repeatOnLifecycle(onLifeCycleState) {
            flow.collect(collector)
        }
    }
}