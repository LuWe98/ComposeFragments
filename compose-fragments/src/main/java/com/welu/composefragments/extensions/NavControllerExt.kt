package com.welu.composefragments.extensions

import androidx.annotation.IdRes
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination

/**
 * Returns either the topmost [NavBackStackEntry] for a [NavDestination.id] or null, if there is none.
 */
fun NavController.findBackStackEntry(@IdRes destinationId: Int): NavBackStackEntry? = try {
    getBackStackEntry(destinationId)
} catch (e: Exception) {
    null
}