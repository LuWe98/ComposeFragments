package com.welu.composefragments.provider

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.welu.composefragments.extensions.findFragmentByClass
import com.welu.composefragments.extensions.navHostFragment
import androidx.navigation.fragment.NavHostFragment
import com.welu.composefragments.extensions.primaryNavigationFragment
import kotlin.reflect.KClass

open class FragmentManagerProvider(
    val provide: FragmentActivity.() -> FragmentManager
) {
    /**
     * Uses the [Fragment.getParentFragmentManager] of the current [Fragment]
     */
    data class CurrentFragment(
        val fragmentManagerType: FragmentManagerType = FragmentManagerType.PARENT
    ): FragmentManagerProvider({
        fragmentManagerType.getManager(primaryNavigationFragment)
    })

    /**
     * Uses the [NavHostFragment] of the [FragmentActivity]
     */
    data class NavHost(
        val fragmentManagerType: FragmentManagerType = FragmentManagerType.PARENT
    ): FragmentManagerProvider({
        fragmentManagerType.getManager(navHostFragment)
    })

    /**
     * Uses the [FragmentActivity.getSupportFragmentManager]
     */
    data object Activity: FragmentManagerProvider({
        supportFragmentManager
    })

    /**
     * Uses the parsed [FragmentManager]
     */
    data class Manager(
        val fragmentManager: FragmentManager,
    ): FragmentManagerProvider({
        fragmentManager
    })

    /**
     * Finds an [Fragment] by its ID and provides the [Fragment.getChildFragmentManager] or the [Fragment.getParentFragmentManager]
     */
    data class FragmentById(
        val fragmentId: Int,
        val fragmentManagerType: FragmentManagerType = FragmentManagerType.PARENT
    ): FragmentManagerProvider({
        val fragment = navHostFragment.childFragmentManager.findFragmentById(fragmentId) ?: primaryNavigationFragment
        fragmentManagerType.getManager(fragment)
    })

    /**
     * Finds an [Fragment] by its Tag and provides the [Fragment.getChildFragmentManager] or the [Fragment.getParentFragmentManager]
     */
    data class FragmentByTag(
        val fragmentTag: String,
        val fragmentManagerType: FragmentManagerType = FragmentManagerType.PARENT
    ): FragmentManagerProvider({
        val fragment = navHostFragment.childFragmentManager.findFragmentByTag(fragmentTag) ?: primaryNavigationFragment
        fragmentManagerType.getManager(fragment)
    })

    /**
     * Finds an [Fragment] by its Class and provides the [Fragment.getChildFragmentManager] or the [Fragment.getParentFragmentManager]
     */
    data class FragmentByClass(
        val fragmentClass: KClass<out Fragment>,
        val fragmentManagerType: FragmentManagerType = FragmentManagerType.PARENT
    ): FragmentManagerProvider({
        val fragment = navHostFragment.childFragmentManager.findFragmentByClass(fragmentClass) ?: primaryNavigationFragment
        fragmentManagerType.getManager(fragment)
    })
}