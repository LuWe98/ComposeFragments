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
inline fun <reified T> Flow<T>.collectOnStarted(
    lifecycleOwner: LifecycleOwner,
    firstTimeDelay: Long = 0L,
    noinline collector: suspend (T) -> Unit
) = collectOnLifecycle(
    lifecycleOwner = lifecycleOwner,
    onLifeCycleState = Lifecycle.State.STARTED,
    firstTimeDelay = firstTimeDelay,
    collector = collector
)

/**
 * Collects the [Flow] when the [Lifecycle] of the provided [LifecycleOwner] reaches [Lifecycle.State.RESUMED]
 */
inline fun <reified T> Flow<T>.collectOnResumed(
    lifecycleOwner: LifecycleOwner,
    firstTimeDelay: Long = 0L,
    noinline collector: suspend (T) -> Unit
) = collectOnLifecycle(
    lifecycleOwner = lifecycleOwner,
    onLifeCycleState = Lifecycle.State.RESUMED,
    firstTimeDelay = firstTimeDelay,
    collector = collector
)

/**
 * Collects the [Flow] when the [Lifecycle] of the provided [LifecycleOwner] reaches [Lifecycle.State.CREATED]
 */
inline fun <reified T> Flow<T>.collectOnCreated(
    lifecycleOwner: LifecycleOwner,
    firstTimeDelay: Long = 0L,
    noinline collector: suspend (T) -> Unit
) = collectOnLifecycle(
    lifecycleOwner = lifecycleOwner,
    onLifeCycleState = Lifecycle.State.CREATED,
    firstTimeDelay = firstTimeDelay,
    collector = collector
)

/**
 * Collects the [Flow] when the [Lifecycle] of the provided [LifecycleOwner] reaches a specific [Lifecycle.State]
 */
inline fun <reified T> Flow<T>.collectOnLifecycle(
    lifecycleOwner: LifecycleOwner,
    onLifeCycleState: Lifecycle.State,
    firstTimeDelay: Long = 0L,
    noinline collector: suspend (T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch(Dispatchers.Main.immediate) {
        delay(firstTimeDelay)
        lifecycleOwner.lifecycle.repeatOnLifecycle(onLifeCycleState) {
            collect(collector)
        }
    }
}