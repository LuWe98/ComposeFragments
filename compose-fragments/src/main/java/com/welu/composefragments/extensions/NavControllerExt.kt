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

val NavController.hasPreviousDestination get(): Boolean = previousBackStackEntry != null

val NavController.previousDestination get(): NavDestination? = previousBackStackEntry?.destination

val NavController.previousDestinationId get(): Int? = previousBackStackEntry?.destination?.id

val NavController.currentDestinationId get(): Int? = currentDestination?.id

fun NavController.isOnBackStack(@IdRes destinationId: Int): Boolean = findBackStackEntry(destinationId) != null

fun NavController.isCurrentDestination(@IdRes destinationId: Int): Boolean = currentDestinationId == destinationId

fun NavController.isPreviousDestination(@IdRes destinationId: Int): Boolean = previousDestinationId == destinationId