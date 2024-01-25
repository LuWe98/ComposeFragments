package com.welu.composefragments.events

import kotlinx.coroutines.flow.Flow

/**
 * An [EventDispatcher] is used to dispatch [DispatchableEvent]s to other components.
 */
interface EventDispatcher<T: DispatchableEvent> {
    val events: Flow<T>
    suspend fun dispatch(event: T)
}