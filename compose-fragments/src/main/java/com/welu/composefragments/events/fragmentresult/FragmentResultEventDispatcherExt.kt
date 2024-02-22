package com.welu.composefragments.events.fragmentresult

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.welu.composefragments.events.base.EventDispatcher
import com.welu.composefragments.provider.NavBackStackEntryProvider
import com.welu.composefragments.result.FragmentResult
import com.welu.composefragments.result.toEvent
import kotlin.reflect.KClass

suspend fun EventDispatcher<in FragmentResultEvent>.dispatch(
    result: FragmentResult
) = dispatch(result.toEvent())

suspend fun EventDispatcher<in FragmentResultEvent>.dispatch(
    key: String,
    result: FragmentResult
) = dispatch(result.toEvent(key))

suspend fun EventDispatcher<in FragmentResultEvent>.dispatch(
    result: FragmentResult,
    @IdRes destinationId: Int
) = dispatch(result.toEvent(destinationId))

suspend fun EventDispatcher<in FragmentResultEvent>.dispatch(
    key: String,
    result: FragmentResult,
    @IdRes destinationId: Int
) = dispatch(result.toEvent(key, destinationId))

suspend fun EventDispatcher<in FragmentResultEvent>.dispatch(
    result: FragmentResult,
    fragmentClass: KClass<Fragment>
) = dispatch(result.toEvent(fragmentClass))

suspend fun EventDispatcher<in FragmentResultEvent>.dispatch(
    key: String,
    result: FragmentResult,
    fragmentClass: KClass<Fragment>
) = dispatch(result.toEvent(key, fragmentClass))

suspend fun EventDispatcher<in FragmentResultEvent>.dispatch(
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) = dispatch(result.toEvent(provider))

suspend fun EventDispatcher<in FragmentResultEvent>.dispatch(
    key: String,
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) = dispatch(result.toEvent(key, provider))