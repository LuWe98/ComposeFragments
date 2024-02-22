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





@Suppress("UNCHECKED_CAST")
fun <T1, T2, T3, T4, T5, T6, R> combine(
    flow: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    flow5: Flow<T5>,
    flow6: Flow<T6>,
    transform: suspend (T1, T2, T3, T4, T5, T6) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(flow, flow2, flow3, flow4, flow5, flow6) { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2,
        args[2] as T3,
        args[3] as T4,
        args[4] as T5,
        args[5] as T6
    )
}


@Suppress("UNCHECKED_CAST")
fun <T1, T2, T3, T4, T5, T6, T7, R> combine(
    flow: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    flow5: Flow<T5>,
    flow6: Flow<T6>,
    flow7: Flow<T7>,
    transform: suspend (T1, T2, T3, T4, T5, T6, T7) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(flow, flow2, flow3, flow4, flow5, flow6, flow7) { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2,
        args[2] as T3,
        args[3] as T4,
        args[4] as T5,
        args[5] as T6,
        args[6] as T7
    )
}


@Suppress("UNCHECKED_CAST")
fun <T1, T2, T3, T4, T5, T6, T7, T8, R> combine(
    flow: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    flow5: Flow<T5>,
    flow6: Flow<T6>,
    flow7: Flow<T7>,
    flow8: Flow<T8>,
    transform: suspend (T1, T2, T3, T4, T5, T6, T7, T8) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(flow, flow2, flow3, flow4, flow5, flow6, flow7, flow8) { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2,
        args[2] as T3,
        args[3] as T4,
        args[4] as T5,
        args[5] as T6,
        args[6] as T7,
        args[7] as T8
    )
}

@Suppress("UNCHECKED_CAST")
fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> combine(
    flow: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    flow5: Flow<T5>,
    flow6: Flow<T6>,
    flow7: Flow<T7>,
    flow8: Flow<T8>,
    flow9: Flow<T9>,
    transform: suspend (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(flow, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9) { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2,
        args[2] as T3,
        args[3] as T4,
        args[4] as T5,
        args[5] as T6,
        args[6] as T7,
        args[7] as T8,
        args[8] as T9
    )
}

@Suppress("UNCHECKED_CAST")
fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> combine(
    flow: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    flow5: Flow<T5>,
    flow6: Flow<T6>,
    flow7: Flow<T7>,
    flow8: Flow<T8>,
    flow9: Flow<T9>,
    flow10: Flow<T10>,
    transform: suspend (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(flow, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10) { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2,
        args[2] as T3,
        args[3] as T4,
        args[4] as T5,
        args[5] as T6,
        args[6] as T7,
        args[7] as T8,
        args[8] as T9,
        args[9] as T10
    )
}