package com.welu.composefragments

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

abstract class ComposeActivity : AppCompatActivity() {

    /**
     * Implement this function in a way that it applies your material theme to the parsed [Composable].
     * This is used by IComposeFragments, in order to apply the correct theme colors.
     *
     * Example:
     *
     *     @Composable
     *     override fun WithTheme(
     *          content: @Composable () -> Unit
     *     ) {
     *         val useDarkTheme by viewModel
     *              .useDarkThemeFlow
     *              .collectAsStateWithLifecycle()
     *
     *         val useDynamicColors by viewModel
     *              .useDynamicColorsFlow
     *              .collectAsStateWithLifecycle()
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

    open fun provideDialogFragmentTheme(): Int = R.style.Theme_ComposeDialogFragment

    open fun provideBottomSheetDialogFragmentTheme(): Int = R.style.Theme_ComposeBottomSheetFragment

    @Composable
    open fun WithFragmentSurface(
        content: @Composable () -> Unit
    ) {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize()
                .statusBarsPadding(),
            content = content
        )
    }

    @Composable
    open fun WithDialogFragmentSurface(
        content: @Composable () -> Unit
    ) {
        Surface(
            color = Color.Transparent,
            contentColor = contentColorFor(MaterialTheme.colorScheme.surface),
            content = content
        )
    }

    @Composable
    open fun WithBottomSheetDialogFragmentSurface(
        content: @Composable () -> Unit
    ) {
        Surface(
            color = Color.Transparent,
            contentColor = contentColorFor(MaterialTheme.colorScheme.surface),
            content = content
        )
    }

}