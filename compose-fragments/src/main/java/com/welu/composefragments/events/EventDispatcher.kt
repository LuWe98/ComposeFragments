package com.welu.composefragments.events

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlin.coroutines.CoroutineContext

/**
 * An [EventDispatcher] is used to dispatch [DispatchableEvent]s to other components.
 */
interface EventDispatcher<T: DispatchableEvent> {

    /**
     * The [DispatchableEvent]s which are cached in this [EventDispatcher].
     */
    val events: Flow<T>


    suspend fun getEvents(): List<T> = events.toList()

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
    fun autonomousDispatch(vararg events: T)
}

/**
 * See [EventDispatcher.dispatch]
 */
suspend inline fun <reified T: DispatchableEvent> EventDispatcher<T>.dispatch(
    vararg eventProviders: DispatchableEventProvider<T>
) {
    val events = eventProviders.map(DispatchableEventProvider<T>::provide)
    dispatch(*events.toTypedArray())
}

/**
 * See [EventDispatcher.autonomousDispatch]
 */
inline fun <reified T: DispatchableEvent> EventDispatcher<T>.autonomousDispatch(
    vararg eventProviders: DispatchableEventProvider<T>
) {
    val events = eventProviders.map(DispatchableEventProvider<T>::provide)
    autonomousDispatch(*events.toTypedArray())
}