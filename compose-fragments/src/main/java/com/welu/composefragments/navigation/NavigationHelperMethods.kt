package com.welu.composefragments.navigation

import androidx.navigation.NavOptions

fun navOptions(optionsBuilder: NavOptions.Builder.() -> Unit): NavOptions = NavOptions.Builder().apply(optionsBuilder).build()