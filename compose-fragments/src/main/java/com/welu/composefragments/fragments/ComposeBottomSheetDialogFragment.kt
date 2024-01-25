package com.welu.composefragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.updateLayoutParams
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.welu.composefragments.R
import com.welu.composefragments.extensions.composeActivity

abstract class ComposeBottomSheetDialogFragment: BottomSheetDialogFragment(), IComposeFragment {

    val bottomSheetDialog get() = dialog as BottomSheetDialog?

    val bottomSheetBehaviour get() = bottomSheetDialog?.behavior

    @Composable
    abstract fun Content()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            composeActivity.WithTheme {
                this@ComposeBottomSheetDialogFragment.Content()
            }
        }
    }

    override fun getTheme(): Int = R.style.Theme_ComposeBottomSheetFragment

    fun forceFullscreenBottomSheet(){
        view?.updateLayoutParams<FrameLayout.LayoutParams> {
            height = resources.displayMetrics.heightPixels
        }
    }

    fun skipHalfExpandedAndCollapsing() {
        bottomSheetBehaviour?.apply {
            this.state = BottomSheetBehavior.STATE_EXPANDED
            isFitToContents = false
        }
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