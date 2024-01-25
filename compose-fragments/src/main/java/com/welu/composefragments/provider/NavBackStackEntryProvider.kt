package com.welu.composefragments.provider

import androidx.annotation.IdRes
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

open class NavBackStackEntryProvider(
    val provide: NavController.() -> NavBackStackEntry
) {
    companion object {
        val current get() = Current
        val previous get() = Previous
        fun id(@IdRes destinationId: Int) = DestinationId(destinationId)
        fun entry(navBackStackEntry: NavBackStackEntry) = Entry(navBackStackEntry)
    }

    data object Current: NavBackStackEntryProvider({ currentBackStackEntry!! })

    data object Previous: NavBackStackEntryProvider({ previousBackStackEntry!! })

    data class DestinationId(@IdRes val id: Int): NavBackStackEntryProvider({ getBackStackEntry(id) })

    data class Entry(val navBackStackEntry: NavBackStackEntry): NavBackStackEntryProvider({ navBackStackEntry })

}