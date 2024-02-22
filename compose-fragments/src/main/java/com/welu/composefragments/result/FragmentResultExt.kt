package com.welu.composefragments.result

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.welu.composefragments.events.fragmentresult.FragmentResultEvent
import com.welu.composefragments.provider.NavBackStackEntryProvider
import kotlin.reflect.KClass

fun FragmentResult.toEvent() = toEvent(
    key = FragmentResult.createResultKey(this::class)
)

fun FragmentResult.toEvent(key: String) = toEvent(
    key = key,
    provider = NavBackStackEntryProvider.Previous
)

fun FragmentResult.toEvent(@IdRes destinationId: Int) = toEvent(
    key = FragmentResult.createResultKey(this::class),
    destinationId = destinationId
)

fun FragmentResult.toEvent(key: String, @IdRes destinationId: Int) = toEvent(
    key = key,
    provider = NavBackStackEntryProvider.Id(destinationId)
)

fun FragmentResult.toEvent(fragmentClass: KClass<Fragment>) = toEvent(
    key = FragmentResult.createResultKey(this::class),
    fragmentClass = fragmentClass
)

fun FragmentResult.toEvent(key: String, fragmentClass: KClass<Fragment>) = toEvent(
    key = key,
    provider = NavBackStackEntryProvider.Class(fragmentClass)
)

fun FragmentResult.toEvent(
    provider: NavBackStackEntryProvider
) = toEvent(
    key = FragmentResult.createResultKey(this::class),
    provider = provider
)

fun FragmentResult.toEvent(
    key: String,
    provider: NavBackStackEntryProvider
) = FragmentResultEvent(
    key = key,
    result = this,
    provider = provider
)