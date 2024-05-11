package com.welu.composefragments.fragments

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import com.welu.composefragments.ComposeActivity

sealed interface IComposeFragment {

    /**
     * Provides the connected [ComposeActivity] of this [IComposeFragment].
     */
    val composeActivity: ComposeActivity

    /**
     * Provides any [ProvidedValue]-Instance for the inflated [Content].
     */
    fun compositionLocalProviders(): Array<ProvidedValue<*>>

    /**
     * This method is used to inflate the [Composable]s of this [IComposeFragment].
     * The default implementation of this method uses [ProvideTheme] and [ProvideSurface].
     */
    @Composable
    fun Content()

    /**
     * Provide the theme of this Application for this specific [IComposeFragment].
     * By default the global [ComposeActivity] theme is used.
     */
    @Composable
    fun ProvideTheme(content: @Composable () -> Unit)

    /**
     * Provide a material surface for this [IComposeFragment]'s [Content].
     * By default the global [ComposeActivity] theme is used.
     */
    @Composable
    fun ProvideSurface(content: @Composable () -> Unit)

}