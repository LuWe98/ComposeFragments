package com.welu.composefragments.result

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavBackStackEntry
import com.welu.composefragments.extensions.collectOnLifecycle
import com.welu.composefragments.extensions.findBackStackEntry
import com.welu.composefragments.extensions.navController

inline fun <reified T : FragmentResult> Fragment.fragmentResultCollector(
    onLifeCycleState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline resultCollector: (T) -> Unit
) {
    fragmentResultCollector(
        key = FragmentResult.createResultKey(T::class),
        onLifeCycleState = onLifeCycleState,
        resultCollector = resultCollector
    )
}

inline fun <reified T : FragmentResult> Fragment.fragmentResultCollector(
    key: String,
    onLifeCycleState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline resultCollector: (T) -> Unit
) {
    val backStackEntry = navController.findBackStackEntry(this) ?: return

    viewLifecycleOwner.fragmentResultCollector(
        key = key,
        forNavBackStackEntry = backStackEntry,
        onLifeCycleState = onLifeCycleState,
        resultCollector = resultCollector
    )
}

inline fun <reified T : FragmentResult> Fragment.fragmentResultCollector(
    @IdRes destinationId: Int,
    onLifeCycleState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline resultCollector: (T) -> Unit
) {
    fragmentResultCollector(
        key = FragmentResult.createResultKey(T::class),
        destinationId = destinationId,
        onLifeCycleState = onLifeCycleState,
        resultCollector = resultCollector
    )
}

inline fun <reified T : FragmentResult> Fragment.fragmentResultCollector(
    key: String,
    @IdRes destinationId: Int,
    onLifeCycleState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline resultCollector: (T) -> Unit
) {
    val backStackEntry = navController.getBackStackEntry(destinationId)

    viewLifecycleOwner.fragmentResultCollector(
        key = key,
        forNavBackStackEntry = backStackEntry,
        onLifeCycleState = onLifeCycleState,
        resultCollector = resultCollector
    )
}

inline fun <reified T : FragmentResult> Fragment.fragmentResultCollector(
    forNavBackStackEntry: NavBackStackEntry,
    onLifeCycleState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline resultCollector: (T) -> Unit
) {
    viewLifecycleOwner.fragmentResultCollector(
        key = FragmentResult.createResultKey(T::class),
        forNavBackStackEntry = forNavBackStackEntry,
        onLifeCycleState = onLifeCycleState,
        resultCollector = resultCollector
    )
}
/**
 *
 */
inline fun <reified T : FragmentResult> LifecycleOwner.fragmentResultCollector(
    key: String,
    forNavBackStackEntry: NavBackStackEntry,
    onLifeCycleState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline resultCollector: (T) -> Unit
) {
    forNavBackStackEntry
        .savedStateHandle
        .getStateFlow<T?>(key, null)
        .collectOnLifecycle(this, onLifeCycleState) { value ->
            if (value == null) return@collectOnLifecycle
            resultCollector(value)
            forNavBackStackEntry.savedStateHandle[key] = null
        }
}