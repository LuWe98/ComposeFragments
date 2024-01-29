package com.welu.composefragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.FloatRange
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.updateLayoutParams
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.welu.composefragments.ComposeActivity
import com.welu.composefragments.extensions.findComposeActivity

abstract class ComposeBottomSheetDialogFragment: BottomSheetDialogFragment(), IComposeFragment {

    final override val composeActivity: ComposeActivity
        get() = findComposeActivity() ?: throw IllegalStateException("ComposeBottomSheetDialogFragment is not hosted in a ComposeActivity.")

    val bottomSheetDialog: BottomSheetDialog?  get()= dialog as BottomSheetDialog?

    val bottomSheetBehaviour: BottomSheetBehavior<FrameLayout>? get() = bottomSheetDialog?.behavior

    open fun compositionLocalProviders(): Array<ProvidedValue<*>> = arrayOf(LocalView provides dialog!!.window!!.decorView)

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
                        composeActivity.WithBottomSheetDialogFragmentSurface {
                            this@ComposeBottomSheetDialogFragment.Content()
                        }
                    }
                }
            }
        }
    }

    override fun getTheme(): Int = composeActivity.provideBottomSheetDialogFragmentTheme()

    fun forceFullscreenBottomSheet(){
        view?.updateLayoutParams<FrameLayout.LayoutParams> {
            height = resources.displayMetrics.heightPixels
        }
    }

    fun skipHalfExpandedAndCollapsing() {
        skipCollapsed()
        skipHalfExpanded()
        expand()
    }

    fun setBottomSheetState(int: Int) {
        bottomSheetBehaviour?.state = int
    }

    fun setHalfExpandedRatio(@FloatRange(0.0, 1.0, fromInclusive = false, toInclusive = false) value: Float) {
        bottomSheetBehaviour?.halfExpandedRatio = value
    }

    fun skipHalfExpanded() {
        setHalfExpandedRatio(0.000000001f)
    }

    fun skipCollapsed() {
        bottomSheetBehaviour?.skipCollapsed = true
    }

    fun expand() {
        setBottomSheetState(BottomSheetBehavior.STATE_EXPANDED)
    }

    fun hide() {
        setBottomSheetState(BottomSheetBehavior.STATE_HIDDEN)
    }

    fun addBottomSheetCallBack(callBack: BottomSheetBehavior.BottomSheetCallback) {
        bottomSheetBehaviour?.addBottomSheetCallback(callBack)
    }

    fun removeBottomSheetCallBack(callBack: BottomSheetBehavior.BottomSheetCallback) {
        bottomSheetBehaviour?.removeBottomSheetCallback(callBack)
    }
}