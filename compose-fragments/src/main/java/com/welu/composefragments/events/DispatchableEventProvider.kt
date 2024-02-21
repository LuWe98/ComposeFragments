package com.welu.composefragments.events

/**
 * Provides a [DispatchableEvent] for easy function interface usage.
 */
fun interface DispatchableEventProvider<out T: DispatchableEvent> {
    fun provide(): T
}