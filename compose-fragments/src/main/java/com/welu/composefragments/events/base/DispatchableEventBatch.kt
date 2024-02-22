package com.welu.composefragments.events.base

/**
 * This class bundles multiple [DispatchableEvent]s which can be send as one unit.
 *
 * This is useful if the processing of one [DispatchableEvent] influences the processing of another [DispatchableEvent], or to avoid race conditions.
 */
data class DispatchableEventBatch(
    val events: List<DispatchableEvent>
): DispatchableEvent {
    companion object {
        operator fun invoke(vararg events: DispatchableEvent) = DispatchableEventBatch(events.toList())
    }
}