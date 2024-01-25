package com.welu.composefragments.result

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.welu.composefragments.extensions.navController
import com.welu.composefragments.provider.FragmentManagerProvider
import com.welu.composefragments.provider.NavBackStackEntryProvider

//------------------------------------------------------------------------------------
// FragmentManager Setters
//------------------------------------------------------------------------------------

fun Fragment.setFragmentResult(
    result: FragmentResult,
    resultKey: String = FragmentResult.createResultKey(result::class),
    requestKey: String = resultKey,
    fragmentManagerProvider: FragmentManagerProvider = FragmentManagerProvider.NavHost()
) {
    requireActivity().setFragmentResult(result, resultKey, requestKey, fragmentManagerProvider)
}

fun Fragment.setFragmentResult(
    result: FragmentResult,
    provider: FragmentManagerProvider
) {
    val resultKey: String = FragmentResult.createResultKey(result::class)
    requireActivity().setFragmentResult(result, resultKey, resultKey, provider)
}

fun FragmentActivity.setFragmentResult(
    result: FragmentResult,
    resultKey: String = FragmentResult.createResultKey(result::class),
    requestKey: String = resultKey,
    provider: FragmentManagerProvider = FragmentManagerProvider.NavHost()
) {
    provider.provide(this).setFragmentResult(result, requestKey, resultKey)
}

/**
 * Blob LOL
 */
fun FragmentManager.setFragmentResult(
    result: FragmentResult,
    resultKey: String = FragmentResult.createResultKey(result::class),
    requestKey: String = resultKey
) {
    setFragmentResult(requestKey, Bundle().apply {
        putParcelable(resultKey, result)
    })
}



//------------------------------------------------------------------------------------
// NavBackStackEntry - Fragment - Setters
//------------------------------------------------------------------------------------
fun Fragment.sendFragmentResult(
    result: FragmentResult
) {
    sendFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result
    )
}

fun Fragment.sendFragmentResult(
    key: String,
    result: FragmentResult
) {
    sendFragmentResultTo(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.previous
    )
}

fun Fragment.sendFragmentResultTo(
    result: FragmentResult,
    @IdRes forDestinationId: Int
) {
    sendFragmentResultTo(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        forDestinationId = forDestinationId
    )
}

fun Fragment.sendFragmentResultTo(
    key: String,
    result: FragmentResult,
    @IdRes forDestinationId: Int
) {
    sendFragmentResultTo(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.id(forDestinationId)
    )
}

fun Fragment.sendFragmentResultTo(
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) {
    sendFragmentResultTo(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        provider = provider
    )
}

fun Fragment.sendFragmentResultTo(
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
    setFragmentResult(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.previous
    )
}

fun NavController.setFragmentResult(
    result: FragmentResult,
    @IdRes forDestinationId: Int
) {
    setFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        forDestinationId = forDestinationId
    )
}

fun NavController.setFragmentResult(
    key: String,
    result: FragmentResult,
    @IdRes forDestinationId: Int
) {
    setFragmentResult(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.id(forDestinationId)
    )
}

fun NavController.setFragmentResult(
    result: FragmentResult,
    provider: NavBackStackEntryProvider
) {
    setFragmentResult(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        provider = provider
    )
}

fun NavController.setFragmentResult(
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