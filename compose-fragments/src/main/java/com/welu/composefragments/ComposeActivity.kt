package com.welu.composefragments

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable

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

}