package com.welu.composefragments.fragments

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import com.welu.composefragments.ComposeActivity

sealed interface IComposeFragment {

    val composeActivity: ComposeActivity

    @Composable
    fun Content()

    fun compositionLocalProviders(): Array<ProvidedValue<*>>

}