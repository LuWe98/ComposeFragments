package com.welu.composefragments

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

abstract class ComposeActivity: AppCompatActivity() {

    /**
     * Implement this function in a way that it applies your composable theme to the parsed [Composable].
     * This is used by ComposeFragments, in order to apply the correct theme colors.
     *
     * Example:
     *
     *     @Composable
     *     override fun WithTheme(content: @Composable () -> Unit) {
     *         val useDarkTheme by viewModel.useDarkThemeFlow.collectAsState()
     *         val useDynamicColors by viewModel.useDynamicColorsFlow.collectAsState()
     *
     *         ApplicationSpecificTheme(
     *             darkTheme = useDarkTheme,
     *             dynamicColor = useDynamicColors,
     *             content = content
     *         )
     *     }
     */
    @Composable
    abstract fun WithTheme(content: @Composable () -> Unit)

    @Composable
    fun WithDialogFragmentSurface(content: @Composable () -> Unit) {
        Surface(
            color = Color.Transparent,
            contentColor = contentColorFor(MaterialTheme.colorScheme.surface),
            content = content
        )
    }

    @Composable
    fun WithBottomSheetDialogFragmentSurface(content: @Composable () -> Unit) {
        Surface(
            color = Color.Transparent,
            contentColor = contentColorFor(MaterialTheme.colorScheme.surface),
            content = content
        )
    }
}