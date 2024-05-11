package com.welu.composefragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.DialogFragment
import com.welu.composefragments.ComposeActivity
import com.welu.composefragments.composables.LocalFragment
import com.welu.composefragments.extensions.findComposeActivity

abstract class ComposeDialogFragment : DialogFragment(), IComposeFragment {

    private var isCancellableOnTouchOutside = true

    final override val composeActivity: ComposeActivity
        get() = findComposeActivity() ?: throw IllegalStateException("ComposeDialogFragment is not hosted in a ComposeActivity.")

    override fun compositionLocalProviders(): Array<ProvidedValue<*>> = arrayOf(
        LocalView provides dialog!!.window!!.decorView,
        LocalFragment provides this
    )

    @Composable
    override fun ProvideTheme(content: @Composable () -> Unit){
        composeActivity.ProvideTheme(content)
    }

    @Composable
    override fun ProvideSurface(content: @Composable () -> Unit){
        composeActivity.ProvideDialogFragmentSurface(content)
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
                            Box(
                                modifier = Modifier
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember(::MutableInteractionSource),
                                        onClick = {
                                            if (isCancellableOnTouchOutside) dismiss()
                                        }
                                    )
                                    .wrapContentSize()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .clickable(
                                            indication = null,
                                            interactionSource = remember(::MutableInteractionSource),
                                            onClick = {}
                                        )
                                        .align(Alignment.Center)
                                ) {
                                    this@ComposeDialogFragment.Content()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getTheme(): Int = composeActivity.provideDialogFragmentTheme()

    fun setCanceledOnTouchOutside(cancellable: Boolean) {
        isCancellableOnTouchOutside = cancellable
        dialog?.setCanceledOnTouchOutside(cancellable)
    }
}