package com.welu.composefragments

import com.welu.composefragments.events.base.DispatchableEvent
import com.welu.composefragments.events.base.EventDispatcher
import com.welu.composefragments.events.fragmentresult.FragmentResultEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ActivityEventDispatcher(
    private val coroutineScope: CoroutineScope
): EventDispatcher<DispatchableEvent> {

    private val eventChannel = Channel<DispatchableEvent>()

    override val events: Flow<DispatchableEvent> = eventChannel.receiveAsFlow()

    override suspend fun dispatch(vararg events: DispatchableEvent) {
        events.forEach { event ->
            eventChannel.send(event)
        }
    }

    override fun delegatingDispatch(vararg events: DispatchableEvent) {
        coroutineScope.launch {
            dispatch(*events)
        }
    }
}

class FragmentResultEventDispatcher(
    private val parentEventDispatcher: EventDispatcher<DispatchableEvent>,
    private val activityScope: CoroutineScope
): EventDispatcher<FragmentResultEvent> {

    private val eventChannel = Channel<FragmentResultEvent>()

    override val events: Flow<FragmentResultEvent> = eventChannel.receiveAsFlow()

    override suspend fun dispatch(vararg events: FragmentResultEvent) {
        events.forEach { event ->
            eventChannel.send(event)
            parentEventDispatcher.dispatch(event)
        }
    }

    override fun delegatingDispatch(vararg events: FragmentResultEvent) {
        activityScope.launch {
            dispatch(*events)
        }
    }
}