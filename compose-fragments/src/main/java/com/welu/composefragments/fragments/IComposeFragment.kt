package com.welu.composefragments.fragments

import androidx.compose.runtime.Composable
import com.welu.composefragments.ComposeActivity

sealed interface IComposeFragment {
    @Composable
    fun Content()

    val composeActivity: ComposeActivity

}