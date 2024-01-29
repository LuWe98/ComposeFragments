package com.welu.composefragments.extensions

import androidx.collection.forEach
import androidx.fragment.app.Fragment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator

/**
 * Sets the [NavDestination.route] of all [NavDestination]s to their [Fragment]s fully qualified name.
 *
 * This can enable the finding of [NavBackStackEntry]s with a corresponding [Fragment] instance or [Fragment] class.
 */
fun NavGraph.setRoutesToClassName(
    skipIfRouteIsPresent: Boolean = true
) {



    nodes.forEach { _, destination ->
        if (skipIfRouteIsPresent && destination.route != null) return@forEach

        when(destination) {
            is FragmentNavigator.Destination -> destination.route = destination.className
            is DialogFragmentNavigator.Destination -> destination.route = destination.className
        }
    }
}