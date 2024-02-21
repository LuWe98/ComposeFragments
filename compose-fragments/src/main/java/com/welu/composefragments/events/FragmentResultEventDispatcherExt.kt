package com.welu.composefragments.events

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.welu.composefragments.provider.NavBackStackEntryProvider
import com.welu.composefragments.result.FragmentResult
import kotlin.reflect.KClass

suspend fun EventDispatcher<FragmentResultEvent>.dispatch(
    result: FragmentResult
) = dispatch(FragmentResultEvent(result))

suspend fun EventDispatcher<FragmentResultEvent>.dispatch(
    key: String,
    result: FragmentResult
) = dispatch(FragmentResultEvent(key, result))

suspend fun EventDispatcher<FragmentResultEvent>.dispatch(
    result: FragmentResult,
    @IdRes destinationId: Int
) = dispatch(FragmentResultEvent(result, destinationId))

suspend fun EventDispatcher<FragmentResultEvent>.dispatch(
    key: String,
    result: FragmentResult,
    @IdRes destinationId: Int
) = dispatch(FragmentResultEvent(key, result, destinationId))

suspend fun EventDispatcher<FragmentResultEvent>.dispatch(
    result: FragmentResult,
    fragmentClass: KClass<Fragment>
) = dispatch(FragmentResultEvent(result, fragmentClass))

suspend fun EventDispatcher<FragmentResultEvent>.dispatch(
    key: String,
    result: FragmentResult,
    fragmentClass: KClass<Fragment>
) = dispatch(FragmentResultEvent(key, result, fragmentClass))

suspend fun EventDispatcher<FragmentResultEvent>.dispatch(
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) = dispatch(FragmentResultEvent(result, provider))

suspend fun EventDispatcher<FragmentResultEvent>.dispatch(
    key: String,
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) = dispatch(FragmentResultEvent(key, result, provider))