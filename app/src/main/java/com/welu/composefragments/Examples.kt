package com.welu.composefragments

import com.welu.composefragments.events.DispatchableEvent
import com.welu.composefragments.events.EventDispatcher
import com.welu.composefragments.events.FragmentResultEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class ActivityEventDispatcher: EventDispatcher<DispatchableEvent> {

    private val eventChannel = Channel<DispatchableEvent>()

    override val events: Flow<DispatchableEvent> = eventChannel.receiveAsFlow()

    override suspend fun dispatch(event: DispatchableEvent) {
        eventChannel.send(event)
    }

}

class FragmentResultEventDispatcher(
    private val parentEventDispatcher: EventDispatcher<DispatchableEvent>
): EventDispatcher<FragmentResultEvent> {

    private val eventChannel = Channel<FragmentResultEvent>()

    override val events: Flow<FragmentResultEvent> = eventChannel.receiveAsFlow()

    override suspend fun dispatch(event: FragmentResultEvent) {
        eventChannel.send(event)
        parentEventDispatcher.dispatch(event)
    }
}