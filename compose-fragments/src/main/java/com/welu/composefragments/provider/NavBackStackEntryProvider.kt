package com.welu.composefragments.provider

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.welu.composefragments.extensions.getBackStackEntry
import kotlin.reflect.KClass

open class NavBackStackEntryProvider(
    val provide: NavController.() -> NavBackStackEntry
) {
    data object Current: NavBackStackEntryProvider({ currentBackStackEntry!! })

    data object Previous: NavBackStackEntryProvider({ previousBackStackEntry!! })

    data class Id(@IdRes val destinationId: Int): NavBackStackEntryProvider({ getBackStackEntry(destinationId) })

    data class Entry(val navBackStackEntry: NavBackStackEntry): NavBackStackEntryProvider({ navBackStackEntry })

    data class Class<T: Fragment>(val clazz: KClass<T>) : NavBackStackEntryProvider({ getBackStackEntry(clazz) })

}