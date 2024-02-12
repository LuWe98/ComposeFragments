package com.welu.composefragments.composables

import androidx.compose.runtime.staticCompositionLocalOf
import com.welu.composefragments.fragments.IComposeFragment

/**
 * Provides the [IComposeFragment] this composable is associated with.
 *
 * This allows the composable to use fragment specific logic.
 */
val LocalFragment = staticCompositionLocalOf<IComposeFragment> {
    noLocalProvidedFor("LocalFragment")
}

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}