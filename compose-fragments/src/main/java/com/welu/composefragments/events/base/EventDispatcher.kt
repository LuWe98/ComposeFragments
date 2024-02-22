package com.welu.composefragments.events.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

/**
 * An [EventDispatcher] is used to dispatch [DispatchableEvent]s to other components.
 */
interface EventDispatcher<T: DispatchableEvent> {

    /**
     * The [DispatchableEvent]s which are cached in this [EventDispatcher].
     */
    val events: Flow<T>

    /**
     * Processes the parsed [DispatchableEvent]s in a [CoroutineContext] of the caller.
     */
    suspend fun dispatch(vararg events: T)

    /**
     * Processes the parsed [DispatchableEvent]s in a separate [CoroutineContext] in this [EventDispatcher].
     * 
     * This can be useful if navigationEvents are dispatched from a [ViewModel] where the viewModelScope could be destroyed while
     * processing the [DispatchableEvent]s
     */
    fun delegatingDispatch(vararg events: T)

}

/**
 * Similar to the regular [dispatch] but this function will wrap the parsed [events] in a [DispatchableEventBatch].
 *
 * Therefore, only the [DispatchableEventBatch] will be send as an event to the [EventDispatcher].
 *
 * This behaviour is useful if the processing of one [DispatchableEvent] influences the processing of another [DispatchableEvent], or to avoid race conditions.
 */
suspend fun EventDispatcher<DispatchableEvent>.batchDispatch(
    vararg events: DispatchableEvent
) {
    dispatch(DispatchableEventBatch(*events))
}

/**
 * Combination of [batchDispatch] and [delegatingDispatch].
 */
fun EventDispatcher<DispatchableEvent>.delegatingBatchDispatch(vararg events: DispatchableEvent) {
    delegatingDispatch(DispatchableEventBatch(*events))
}