package com.welu.composefragments.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
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

    open val applyStatusBarPadding : Boolean = true

    override fun compositionLocalProviders(): Array<ProvidedValue<*>> = arrayOf(
        LocalFragment provides this
    )

    @Composable
    override fun ProvideTheme(content: @Composable () -> Unit){
        composeActivity.ProvideTheme(content)
    }

    @Composable
    override fun ProvideSurface(content: @Composable () -> Unit){
        Log.d("manual", "L: " + applyStatusBarPadding)
        composeActivity.ProvideFragmentSurface(applyStatusBarPadding, content)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val providers = compositionLocalProviders()

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                CompositionLocalProvider(*providers) {
                    ProvideTheme {
                        ProvideSurface {
                            this@ComposeFragment.Content()
                        }
                    }
                }
            }
        }
    }
}