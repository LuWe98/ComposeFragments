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
fun Fragment.setFragmentResult(
    result: FragmentResult
) {
    setFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result
    )
}

fun Fragment.setFragmentResult(
    key: String,
    result: FragmentResult
) {
    setFragmentResultTo(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Previous
    )
}

fun Fragment.setFragmentResultTo(
    result: FragmentResult,
    @IdRes forDestinationId: Int
) {
    setFragmentResultTo(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        forDestinationId = forDestinationId
    )
}

fun Fragment.setFragmentResultTo(
    key: String,
    result: FragmentResult,
    @IdRes forDestinationId: Int
) {
    setFragmentResultTo(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Id(forDestinationId)
    )
}

fun <T: Fragment> Fragment.setFragmentResultTo(
    result: FragmentResult,
    forFragment: KClass<T>
) {
    setFragmentResultTo(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        forFragment = forFragment
    )
}

fun <T: Fragment> Fragment.setFragmentResultTo(
    key: String,
    result: FragmentResult,
    forFragment: KClass<T>
) {
    setFragmentResultTo(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Class(forFragment)
    )
}

inline fun <reified T: Fragment> Fragment.setFragmentResultTo(
    result: FragmentResult
) {
    setFragmentResultTo(
        result = result,
        forFragment = T::class
    )
}

inline fun <reified T: Fragment> Fragment.setFragmentResultTo(
    key: String,
    result: FragmentResult
) {
    setFragmentResultTo(
        key = key,
        result = result,
        forFragment = T::class
    )
}


fun Fragment.setFragmentResultTo(
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) {
    setFragmentResultTo(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        provider = provider
    )
}

fun Fragment.setFragmentResultTo(
    key: String,
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) {
    provider.provide(navController).setFragmentResult(
        key = key,
        result = result
    )
}


//------------------------------------------------------------------------------------
// NavBackStackEntry - NavController - Setters
//------------------------------------------------------------------------------------
fun NavController.setFragmentResult(
    result: FragmentResult
) {
    setFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result
    )
}

fun NavController.setFragmentResult(
    key: String,
    result: FragmentResult
) {
    setFragmentResultTo(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Previous
    )
}

fun NavController.setFragmentResultTo(
    result: FragmentResult,
    @IdRes forDestinationId: Int
) {
    setFragmentResultTo(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        forDestinationId = forDestinationId
    )
}

fun NavController.setFragmentResultTo(
    key: String,
    result: FragmentResult,
    @IdRes forDestinationId: Int
) {
    setFragmentResultTo(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Id(forDestinationId)
    )
}

fun <T: Fragment> NavController.setFragmentResultTo(
    result: FragmentResult,
    forFragment: KClass<T>
) {
    setFragmentResultTo(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        forFragment = forFragment
    )
}

fun <T: Fragment> NavController.setFragmentResultTo(
    key: String,
    result: FragmentResult,
    forFragment: KClass<T>
) {
    setFragmentResultTo(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Class(forFragment)
    )
}

inline fun <reified T: Fragment> NavController.setFragmentResultTo(
    result: FragmentResult
) {
    setFragmentResultTo(
        result = result,
        forFragment = T::class
    )
}

inline fun <reified T: Fragment> NavController.setFragmentResultTo(
    key: String,
    result: FragmentResult
) {
    setFragmentResultTo(
        key = key,
        result = result,
        forFragment = T::class
    )
}


fun NavController.setFragmentResultTo(
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) {
    setFragmentResultTo(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        provider = provider
    )
}

fun NavController.setFragmentResultTo(
    key: String,
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) {
    provider.provide(this).setFragmentResult(key, result)
}


//------------------------------------------------------------------------------------
// NavBackStackEntry - Setters
//------------------------------------------------------------------------------------
fun NavBackStackEntry.setFragmentResult(
    result: FragmentResult
) {
    setFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result
    )
}

fun NavBackStackEntry.setFragmentResult(
    key: String,
    result: FragmentResult
) {
    savedStateHandle[key] = result
}