package com.welu.composefragments.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDestination
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import kotlin.reflect.KClass

/**
 * Checks if the given [Fragment] is connected to a [NavDestination].
 */
fun <T: Fragment> NavDestination?.isDestinationOf(fragment: Fragment): Boolean = isDestinationOf(fragment::class)

/**
 * Checks if the given [Fragment] is connected to a [NavDestination].
 */
inline fun <reified T: Fragment> NavDestination?.isDestinationOf(): Boolean = isDestinationOf(T::class)

/**
 * Checks if the given [Fragment] class is connected to a [NavDestination].
 */
fun <T: Fragment> NavDestination?.isDestinationOf(fragmentClass: KClass<T>): Boolean = fragmentClass.qualifiedName?.let {
    it == getFragmentClassName()
} ?: false


/**
 * Tries to get the [FragmentNavigator.Destination.className] or [DialogFragmentNavigator.Destination.className].
 *
 * Otherwise null is returned.
 */
fun NavDestination?.getFragmentClassName(): String? =  when (this) {
    is FragmentNavigator.Destination-> className
    is DialogFragmentNavigator.Destination -> className
    else -> null
}