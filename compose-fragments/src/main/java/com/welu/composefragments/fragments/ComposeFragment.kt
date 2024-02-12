package com.welu.composefragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.welu.composefragments.ComposeActivity
import com.welu.composefragments.composables.LocalFragment
import com.welu.composefragments.extensions.findComposeActivity

abstract class ComposeFragment: Fragment(), IComposeFragment {

    final override val composeActivity: ComposeActivity
        get() = findComposeActivity() ?: throw IllegalStateException("ComposeFragment is not hosted in a ComposeActivity.")

    override fun compositionLocalProviders(): Array<ProvidedValue<*>> = arrayOf(
        LocalFragment provides this
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeActivity = this.composeActivity
        val providers = compositionLocalProviders()

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                CompositionLocalProvider(*providers) {
                    composeActivity.WithTheme {
                        composeActivity.WithFragmentSurface {
                            this@ComposeFragment.Content()
                        }
                    }
                }
            }
        }
    }
}