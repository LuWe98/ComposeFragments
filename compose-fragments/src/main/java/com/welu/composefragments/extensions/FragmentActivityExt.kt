package com.welu.composefragments.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.welu.composefragments.R

val FragmentActivity.navHostFragment get() : NavHostFragment = supportFragmentManager.findFragmentById(R.id.composeActivityFragmentContainerViewId) as NavHostFragment

val FragmentActivity.navController get(): NavController = navHostFragment.navController

val FragmentActivity.primaryNavigationFragment: Fragment get() = navHostFragment.primaryNavigationFragment

val FragmentActivity.currentFragment: Fragment get() = navHostFragment.currentFragment