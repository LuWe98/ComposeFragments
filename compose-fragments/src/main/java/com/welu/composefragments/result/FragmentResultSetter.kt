package com.welu.composefragments.result

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.welu.composefragments.extensions.navController
import com.welu.composefragments.provider.NavBackStackEntryProvider
import kotlin.reflect.KClass


//------------------------------------------------------------------------------------
// NavBackStackEntry - Fragment - Setters
//------------------------------------------------------------------------------------
/**
 * Sends a [result] to the [NavController.previousBackStackEntry].
 */
fun Fragment.sendFragmentResultToPrevious(
    result: FragmentResult
) {
    sendFragmentResultToPrevious(
        key = FragmentResult.createResultKey(result::class),
        result = result
    )
}

/**
 * Sends a [result] for a specified [key] to the [NavController.previousBackStackEntry].
 */
fun Fragment.sendFragmentResultToPrevious(
    key: String,
    result: FragmentResult
) {
    sendFragmentResult(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Previous
    )
}

fun Fragment.sendFragmentResult(
    result: FragmentResult,
    @IdRes toDestination: Int
) {
    sendFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        toDestination = toDestination
    )
}

fun Fragment.sendFragmentResult(
    key: String,
    result: FragmentResult,
    @IdRes toDestination: Int
) {
    sendFragmentResult(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Id(toDestination)
    )
}

fun <T: Fragment> Fragment.sendFragmentResult(
    result: FragmentResult,
    toFragment: KClass<T>
) {
    sendFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        toFragment = toFragment
    )
}

fun <T: Fragment> Fragment.sendFragmentResult(
    key: String,
    result: FragmentResult,
    toFragment: KClass<T>
) {
    sendFragmentResult(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Class(toFragment)
    )
}

inline fun <reified T: Fragment> Fragment.sendFragmentResult(
    result: FragmentResult
) {
    sendFragmentResult(
        result = result,
        toFragment = T::class
    )
}

inline fun <reified T: Fragment> Fragment.sendFragmentResult(
    key: String,
    result: FragmentResult
) {
    sendFragmentResult(
        key = key,
        result = result,
        toFragment = T::class
    )
}


fun Fragment.sendFragmentResult(
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) {
    sendFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        provider = provider
    )
}

fun Fragment.sendFragmentResult(
    key: String,
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) {
    provider.provide(navController).sendFragmentResult(
        key = key,
        result = result
    )
}


//------------------------------------------------------------------------------------
// NavBackStackEntry - NavController - Setters
//------------------------------------------------------------------------------------
fun NavController.sendFragmentResultToPrevious(
    result: FragmentResult
) {
    sendFragmentResultToPrevious(
        key = FragmentResult.createResultKey(result::class),
        result = result
    )
}

fun NavController.sendFragmentResultToPrevious(
    key: String,
    result: FragmentResult
) {
    sendFragmentResult(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Previous
    )
}

fun NavController.sendFragmentResult(
    result: FragmentResult,
    @IdRes toDestination: Int
) {
    sendFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        toDestination = toDestination
    )
}

fun NavController.sendFragmentResult(
    key: String,
    result: FragmentResult,
    @IdRes toDestination: Int
) {
    sendFragmentResult(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Id(toDestination)
    )
}

fun <T: Fragment> NavController.sendFragmentResult(
    result: FragmentResult,
    toFragment: KClass<T>
) {
    sendFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        toFragment = toFragment
    )
}

fun <T: Fragment> NavController.sendFragmentResult(
    key: String,
    result: FragmentResult,
    toFragment: KClass<T>
) {
    sendFragmentResult(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Class(toFragment)
    )
}

inline fun <reified T: Fragment> NavController.sendFragmentResult(
    result: FragmentResult
) {
    sendFragmentResult(
        result = result,
        toFragment = T::class
    )
}

inline fun <reified T: Fragment> NavController.sendFragmentResult(
    key: String,
    result: FragmentResult
) {
    sendFragmentResult(
        key = key,
        result = result,
        toFragment = T::class
    )
}


fun NavController.sendFragmentResult(
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) {
    sendFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        provider = provider
    )
}

fun NavController.sendFragmentResult(
    key: String,
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) {
    provider.provide(this).sendFragmentResult(key, result)
}


//------------------------------------------------------------------------------------
// NavBackStackEntry - Setters
//------------------------------------------------------------------------------------
fun NavBackStackEntry.sendFragmentResult(
    result: FragmentResult
) {
    sendFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result
    )
}

fun NavBackStackEntry.sendFragmentResult(
    key: String,
    result: FragmentResult
) {
    savedStateHandle[key] = result
}