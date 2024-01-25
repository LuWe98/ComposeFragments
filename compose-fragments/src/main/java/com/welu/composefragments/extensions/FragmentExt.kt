package com.welu.composefragments.extensions

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.welu.composefragments.ComposeActivity
import kotlin.reflect.KClass

val NavHostFragment.primaryNavigationFragment get(): Fragment = childFragmentManager.primaryNavigationFragment!!

val NavHostFragment.currentFragment get(): Fragment = childFragmentManager.fragments.last()

val Fragment.navHostFragment get() : NavHostFragment = requireActivity().navHostFragment

val Fragment.navController get(): NavController = navHostFragment.navController

val Fragment.composeActivity get() : ComposeActivity {
    val activity = this.requireActivity()
    return (activity as? ComposeActivity) ?: throw IllegalStateException("The related activity is not a ComposeActivity.")
}

val Fragment.isDialogDestination get() = isDialogFragment || isBottomSheetDialogFragment

val Fragment.isDialogFragment get() = this is DialogFragment

val Fragment.isBottomSheetDialogFragment get() = this is BottomSheetDialogFragment