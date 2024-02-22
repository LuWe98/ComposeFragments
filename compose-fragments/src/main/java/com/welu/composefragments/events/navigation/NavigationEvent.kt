package com.welu.composefragments.events.navigation

import androidx.navigation.NavController
import com.welu.composefragments.ComposeActivity
import com.welu.composefragments.events.base.DispatchableEvent
import com.welu.composefragments.extensions.navController

/**
 * A [NavigationEvent] can be used to notify a [ComposeActivity] to navigate to another screen.
 */
abstract class NavigationEvent(
    val navAction: NavController.(ComposeActivity) -> Unit
) : DispatchableEvent {
    open fun execute(activity: ComposeActivity) {
        navAction(activity.navController, activity)
    }
}

fun navEvent(
    navAction: NavController.(ComposeActivity) -> Unit
) = object : NavigationEvent(navAction) {}