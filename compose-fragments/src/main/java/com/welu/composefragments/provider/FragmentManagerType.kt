package com.welu.composefragments.provider

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

enum class FragmentManagerType {
    CHILD,
    PARENT;

    fun getManager(fragment: Fragment): FragmentManager = when (this) {
        CHILD -> fragment.childFragmentManager
        PARENT -> fragment.parentFragmentManager
    }
}