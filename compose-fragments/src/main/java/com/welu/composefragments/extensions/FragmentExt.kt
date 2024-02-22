package com.welu.composefragments.extensions

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.welu.composefragments.ComposeActivity
import com.welu.composefragments.fragments.IComposeFragment

val NavHostFragment.primaryNavigationFragment get(): Fragment = childFragmentManager.primaryNavigationFragment!!

val NavHostFragment.currentFragment get(): Fragment = childFragmentManager.fragments.last()

val Fragment.navHostFragment get() : NavHostFragment = requireActivity().navHostFragment

val Fragment.navController get(): NavController = navHostFragment.navController

fun Fragment.findActivity(): FragmentActivity? = context?.getActivity()

val Fragment.isDialogDestination get() = isDialogFragment || isBottomSheetDialogFragment

val Fragment.isDialogFragment get() = this is DialogFragment

val Fragment.isBottomSheetDialogFragment get() = this is BottomSheetDialogFragment

val Fragment.isOnBackStack get() = navController.isOnBackStack(this::class)

/**
 * Tries to find a [ComposeActivity] for this [Fragment].
 *
 * An [IComposeFragment] can use the [IComposeFragment.composeActivity] property instead.
 */
fun Fragment.findComposeActivity(): ComposeActivity? = findActivity() as? ComposeActivity