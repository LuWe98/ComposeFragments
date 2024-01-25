package com.welu.composefragments.result

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.annotation.IdRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavBackStackEntry
import com.welu.composefragments.extensions.collectOnLifecycle
import com.welu.composefragments.extensions.findBackStackEntry
import com.welu.composefragments.extensions.navController
import com.welu.composefragments.extensions.requireNavController
import com.welu.composefragments.provider.FragmentManagerProvider
import kotlinx.coroutines.launch

//------------------------------------------------------------------------------------
// FragmentManager Listeners
//------------------------------------------------------------------------------------
inline fun <reified FragmentResultType : FragmentResult> Fragment.fragmentResultListener(
    crossinline action: (FragmentResultType) -> Unit
) {
    val resultKey = FragmentResult.createResultKey(FragmentResultType::class)
    fragmentResultListener(resultKey, resultKey, FragmentManagerProvider.NavHost(), action)
}

inline fun <reified FragmentResultType : FragmentResult> Fragment.fragmentResultListener(
    fragmentManagerProvider: FragmentManagerProvider,
    crossinline action: (FragmentResultType) -> Unit
) {
    val resultKey = FragmentResult.createResultKey(FragmentResultType::class)
    fragmentResultListener(resultKey, resultKey, fragmentManagerProvider, action)
}

inline fun <reified FragmentResultType : FragmentResult> Fragment.fragmentResultListener(
    resultKey: String,
    requestKey: String = resultKey,
    crossinline action: (FragmentResultType) -> Unit
) {
    fragmentResultListener(resultKey, requestKey, FragmentManagerProvider.NavHost(), action)
}

inline fun <reified FragmentResultType : FragmentResult> Fragment.fragmentResultListener(
    resultKey: String,
    requestKey: String = resultKey,
    fragmentManagerProvider: FragmentManagerProvider,
    crossinline action: (FragmentResultType) -> Unit
) {
    fragmentManagerProvider.provide(requireActivity()).setFragmentResultListener(requestKey, this) { _, bundle ->
        if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(resultKey, FragmentResultType::class.java)?.let(action)
        } else {
            bundle.getParcelable<FragmentResultType>(resultKey)?.let(action)
        }
    }
}


//------------------------------------------------------------------------------------
// NavBackStackEntry Listeners
//------------------------------------------------------------------------------------
inline fun <reified T : FragmentResult> Fragment.fragmentResultCollector(
    crossinline callback: (T) -> Unit
) {
    viewLifecycleOwner.fragmentResultCollector(
        key = FragmentResult.createResultKey(T::class),
        forNavBackStackEntry = navController.currentBackStackEntry ?: return,
        callback = callback
    )
}

inline fun <reified T : FragmentResult> Fragment.fragmentResultCollector(
    key: String,
    crossinline callback: (T) -> Unit
) {
    viewLifecycleOwner.fragmentResultCollector(
        key = key,
        forNavBackStackEntry = navController.currentBackStackEntry ?: return,
        callback = callback
    )
}

inline fun <reified T : FragmentResult> Fragment.fragmentResultCollector(
    forNavBackStackEntry: NavBackStackEntry,
    crossinline callback: (T) -> Unit
) {
    viewLifecycleOwner.fragmentResultCollector(
        key = FragmentResult.createResultKey(T::class),
        forNavBackStackEntry = forNavBackStackEntry,
        callback = callback
    )
}

inline fun <reified T : FragmentResult> Fragment.fragmentResultCollector(
    @IdRes destinationId: Int,
    crossinline callback: (T) -> Unit
) {
    viewLifecycleOwner.fragmentResultCollector(
        key = FragmentResult.createResultKey(T::class),
        forNavBackStackEntry = navController.getBackStackEntry(destinationId),
        callback = callback
    )
}

inline fun <reified T : FragmentResult> Fragment.fragmentResultCollector(
    key: String,
    @IdRes destinationId: Int,
    crossinline callback: (T) -> Unit
) {
    viewLifecycleOwner.fragmentResultCollector(
        key = key,
        forNavBackStackEntry = navController.getBackStackEntry(destinationId),
        callback = callback
    )
}

/**
 *
 */
inline fun <reified T : FragmentResult> LifecycleOwner.fragmentResultCollector(
    key: String,
    forNavBackStackEntry: NavBackStackEntry,
    crossinline callback: (T) -> Unit
) {
    forNavBackStackEntry
        .savedStateHandle
        .getStateFlow<T?>(key, null)
        .collectOnLifecycle(this, Lifecycle.State.STARTED) { value ->
            if (value == null) return@collectOnLifecycle
            callback(value)
            forNavBackStackEntry.savedStateHandle[key] = null
        }
}


//------------------------------------------------------------------------------------
// NavBackStackEntry ComposableListeners
//------------------------------------------------------------------------------------
@Composable
inline fun <reified T : FragmentResult> FragmentResultCollector(
    crossinline callback: (T) -> Unit
) {
    FragmentResultCollector(
        key = FragmentResult.createResultKey(T::class),
        callback = callback
    )
}

@Composable
inline fun <reified T : FragmentResult> FragmentResultCollector(
    key: String,
    crossinline callback: (T) -> Unit
) {
    val navController = LocalContext.requireNavController()
    val backStackEntry = remember { navController.currentBackStackEntry }

    if (backStackEntry != null) {
        FragmentResultCollector(
            key = key,
            backStackEntry = backStackEntry,
            callback = callback
        )
    }
}

@Composable
inline fun <reified T : FragmentResult> FragmentResultCollector(
    @IdRes destinationId: Int,
    crossinline callback: (T) -> Unit
) {
    FragmentResultCollector(
        key = FragmentResult.createResultKey(T::class),
        destinationId = destinationId,
        callback = callback
    )
}

@Composable
inline fun <reified T : FragmentResult> FragmentResultCollector(
    key: String,
    @IdRes destinationId: Int,
    crossinline callback: (T) -> Unit
) {
    val navController = LocalContext.requireNavController()
    val backStackEntry = remember { navController.findBackStackEntry(destinationId) }

    if (backStackEntry != null) {
        FragmentResultCollector(
            key = key,
            backStackEntry = backStackEntry,
            callback = callback
        )
    }
}

@Composable
inline fun <reified T : FragmentResult> FragmentResultCollector(
    backStackEntry: NavBackStackEntry,
    crossinline callback: (T) -> Unit
) {
    FragmentResultCollector(
        key = FragmentResult.createResultKey(T::class),
        backStackEntry = backStackEntry,
        callback = callback
    )
}

@Composable
inline fun <reified T : FragmentResult> FragmentResultCollector(
    key: String,
    backStackEntry: NavBackStackEntry,
    crossinline callback: (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val flow = backStackEntry.savedStateHandle.getStateFlow<T?>(key, null)

    DisposableEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect {
                    if (it == null) return@collect
                    callback(it)
                    backStackEntry.savedStateHandle[key] = null
                }
            }
        }.let {
            onDispose(it::cancel)
        }
    }
}