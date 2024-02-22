package com.welu.composefragments.events.fragmentresult

import androidx.navigation.NavBackStackEntry
import com.welu.composefragments.events.base.DispatchableEvent
import com.welu.composefragments.provider.NavBackStackEntryProvider
import com.welu.composefragments.result.FragmentResult

/**
 * Represents a result which can be set for a specific [NavBackStackEntry].
 *
 * This is used to navigate back with a specific result
 */
class FragmentResultEvent(
    val key: String,
    val result: FragmentResult,
    val provider: NavBackStackEntryProvider
) : DispatchableEvent