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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.DialogFragment
import com.welu.composefragments.R
import com.welu.composefragments.extensions.composeActivity

abstract class ComposeDialogFragment : DialogFragment(), IComposeFragment {

    private var isCancellableOnTouchOutside = true

    @Composable
    abstract fun Content()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeActivity = this.composeActivity

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                composeActivity.WithTheme {
                    composeActivity.WithDialogFragmentSurface {
                        Box(
                            modifier = Modifier
                                .clickable(
                                    indication = null,
                                    interactionSource = remember(::MutableInteractionSource),
                                    onClick = {
                                        if (isCancellableOnTouchOutside) {
                                            dismiss()
                                        }
                                    }
                                ).wrapContentSize()
                        ) {
                            Box(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clickable(enabled = false) {}
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

    override fun getTheme(): Int = R.style.Theme_ComposeDialogFragment

    fun setCanceledOnTouchOutside(cancellable: Boolean) {
        isCancellableOnTouchOutside = cancellable
        dialog?.setCanceledOnTouchOutside(cancellable)
    }
}