package com.welu.composefragments.events

/**
 * This is an marker interface, which marks events that can be dispatched to an [EventDispatcher].
 *
 * Property lambdas nested in a [DispatchableEvent] should not rely on properties outside of the lambda scope.
 * Otherwise it is possible, that the processing on a [DispatchableEvent] leads to an Exception.
 */
interface DispatchableEvent