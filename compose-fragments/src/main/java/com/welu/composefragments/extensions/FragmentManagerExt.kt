package com.welu.composefragments.extensions

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlin.reflect.KClass

fun FragmentManager.findFragmentByClass(fragmentClass: KClass<out Fragment>, searchChildren: Boolean = true): Fragment? {
    fragments.reversed().forEach { fragment ->
        if (fragment::class == fragmentClass) {
            return@findFragmentByClass fragment
        }

        if (searchChildren) {
            fragment.childFragmentManager.findFragmentByClass(fragmentClass, true)?.let { nestedFragment ->
                return@findFragmentByClass nestedFragment
            }
        }
    }
    return null
}