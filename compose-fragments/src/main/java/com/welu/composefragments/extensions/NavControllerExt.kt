package com.welu.composefragments.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import com.welu.composefragments.navigation.navOptions
import kotlin.reflect.KClass


//--------------------------------------
// NavController basic helper extensions
//--------------------------------------

/**
 * Returns either the topmost [NavBackStackEntry] for a [NavDestination.id] or null, if there is none.
 */
fun NavController.findBackStackEntry(@IdRes destinationId: Int): NavBackStackEntry? = try {
    getBackStackEntry(destinationId)
} catch (e: Exception) {
    null
}


/**
 * Returns either the topmost [NavBackStackEntry] for a [NavDestination.route] or null, if there is none.
 */
fun NavController.findBackStackEntry(route: String): NavBackStackEntry? = try {
    getBackStackEntry(route)
} catch (e: Exception) {
    null
}


/**
 * Checks if there is any [NavController.previousBackStackEntry].
 */
val NavController.hasPreviousDestination get(): Boolean = previousBackStackEntry != null


/**
 * Returns the previous [NavDestination].
 */
val NavController.previousDestination get(): NavDestination? = previousBackStackEntry?.destination


/**
 * Returns the previous [NavDestination.id]
 */
val NavController.previousDestinationId get(): Int? = previousDestination?.id


/**
 * Returns the current [NavDestination.id]
 */
val NavController.currentDestinationId get(): Int? = currentDestination?.id


/**
 * Returns the previous [NavDestination.route]
 */
val NavController.previousDestinationRoute get(): String? = previousDestination?.route


/**
 * Returns the current [NavDestination.route]
 */
val NavController.currentDestinationRoute get(): String? = currentDestination?.route


/**
 * Returns the current [FragmentNavigator.Destination.className] or [DialogFragmentNavigator.Destination.className] of the current [NavDestination]
 */
val NavController.currentDestinationFragmentClassName get(): String? = currentDestination?.getFragmentClassName()


/**
 * Returns the current [FragmentNavigator.Destination.className] or [DialogFragmentNavigator.Destination.className] of the current [NavDestination]
 */
val NavController.previousDestinationFragmentClassName get(): String? = previousDestination?.getFragmentClassName()


/**
 * Checks if the given [NavDestination.id] is on the [NavController] backstack.
 */
fun NavController.isOnBackStack(@IdRes destinationId: Int): Boolean = findBackStackEntry(destinationId) != null


/**
 * Checks if the given [NavDestination.route] is on the [NavController] backstack.
 */
fun NavController.isOnBackStack(route: String): Boolean = findBackStackEntry(route) != null


/**
 * Checks if the given [NavDestination.id] is the current [NavDestination]
 */
fun NavController.isCurrentDestination(@IdRes destinationId: Int): Boolean = currentDestinationId == destinationId


/**
 * Checks if the given [NavDestination.id] is the previous [NavDestination]
 */
fun NavController.isPreviousDestination(@IdRes destinationId: Int): Boolean = previousDestinationId == destinationId


/**
 * Checks if the given [NavDestination.route] is the current [NavDestination]
 */
fun NavController.isCurrentDestination(route: String): Boolean = currentDestinationRoute == route


/**
 * Checks if the given [NavDestination.route] is the previous [NavDestination]
 */
fun NavController.isPreviousDestination(route: String): Boolean = previousDestinationRoute == route



//--------------------------------------
// NavController fragment helper extensions
//--------------------------------------

/**
 * Checks if the given [Fragment] is the topmost [NavDestination]
 */
fun <T : Fragment> NavController.isCurrentDestination(fragment: T): Boolean = isCurrentDestination(fragment::class)


/**
 * Checks if the given [Fragment] [KClass] is the topmost [NavDestination]
 */
fun <T : Fragment> NavController.isCurrentDestination(fragmentClass: KClass<T>): Boolean = currentDestination.isDestinationOf(fragmentClass)


/**
 * Checks if the given [Fragment] is the previous [NavDestination]
 */
fun <T : Fragment> NavController.isPreviousDestination(fragment: T): Boolean = isPreviousDestination(fragment::class)


/**
 * Checks if the given [Fragment] [KClass] is the previous [NavDestination]
 */
fun <T : Fragment> NavController.isPreviousDestination(fragmentClass: KClass<T>): Boolean = previousDestination.isDestinationOf(fragmentClass)


/**
 * Returns the topmost [NavBackStackEntry] for a [Fragment].
 *
 * Only works if the route of each [NavDestination] is equal to the fully qualified name of the corresponding [Fragment].
 */
fun <T: Fragment> NavController.getBackStackEntry(fragment: T): NavBackStackEntry = getBackStackEntry(fragment::class)


/**
 * Returns the topmost [NavBackStackEntry] for a [Fragment] class.
 *
 * Only works if the route of each [NavDestination] is equal to the fully qualified name of the corresponding [Fragment].
 */
fun <T: Fragment> NavController.getBackStackEntry(fragmentClass: KClass<T>): NavBackStackEntry = getBackStackEntry(route = fragmentClass.qualifiedName!!)


/**
 * Returns either the topmost [NavBackStackEntry] for a [Fragment] or null, if there is none.
 *
 * Only works if the route of each [NavDestination] is equal to the fully qualified name of the corresponding [Fragment].
 */
fun <T: Fragment> NavController.findBackStackEntry(fragment: T): NavBackStackEntry? = findBackStackEntry(fragment::class)


/**
 * Returns either the topmost [NavBackStackEntry] for a [Fragment] class or null, if there is none.
 *
 * Only works if the route of each [NavDestination] is equal to the fully qualified name of the corresponding [Fragment].
 */
fun <T: Fragment> NavController.findBackStackEntry(fragmentClass: KClass<T>): NavBackStackEntry? = findBackStackEntry(fragmentClass.qualifiedName!!)


/**
 * Checks if the given [Fragment] is on the [NavController] backstack.
 *
 * Only works if the route of each [NavDestination] is equal to the fully qualified name of the corresponding [Fragment].
 */
fun <T: Fragment> NavController.isOnBackStack(fragment: T): Boolean = isOnBackStack(fragment::class)


/**
 * Checks if the given [Fragment] class is on the [NavController] backstack.
 *
 * Only works if the route of each [NavDestination] is equal to the fully qualified name of the corresponding [Fragment].
 */
fun <T: Fragment> NavController.isOnBackStack(fragmentClass: KClass<T>): Boolean = isOnBackStack(fragmentClass.qualifiedName!!)



//--------------------------------------
// NavController navigation extensions
//--------------------------------------
fun NavController.navigate(
    directions: NavDirections,
    optionsBuilder: NavOptions.Builder.() -> Unit
){
    navigate(directions, navOptions(optionsBuilder))
}


/**
 * Navigate with the given [NavDirections] and [NavOptions.Builder] if the [NavController.currentDestinationId] is equal to the expected [expectedCurrentDestination].
 */
fun NavController.navigateIfNew(
    directions: NavDirections,
    @IdRes expectedCurrentDestination: Int,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(expectedCurrentDestination)) return

    navigate(directions, optionsBuilder)
}


/**
 * Navigate with the given [NavDirections] and [NavOptions.Builder] if the [NavController.currentDestinationRoute] is equal to the expected [expectedCurrentRoute].
 */
fun NavController.navigateIfNew(
    directions: NavDirections,
    expectedCurrentRoute: String,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(expectedCurrentRoute)) return

    navigate(directions, optionsBuilder)
}


/**
 * Navigate with the given [NavDirections] and [NavOptions.Builder] if the [NavController.currentDestinationFragmentClassName] is equal to the expected [expectedCurrentFragment].
 */
fun <T: Fragment> NavController.navigateIfNew(
    directions: NavDirections,
    expectedCurrentFragment: KClass<T>,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(expectedCurrentFragment)) return

    navigate(directions, optionsBuilder)
}


/**
 * Navigate with the given [NavDirections] and pops the the backstack to the given [popUpToDestination].
 */
fun NavController.navigateAndPopUpTo(
    directions: NavDirections,
    @IdRes popUpToDestination: Int,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    navigate(directions){
        apply(optionsBuilder)
        setPopUpTo(popUpToDestination, inclusive, saveState)
    }
}


/**
 * Navigate with the given [NavDirections] and pops the the backstack to the given [popUpToRoute].
 */
fun NavController.navigateAndPopUpTo(
    directions: NavDirections,
    popUpToRoute: String,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    navigate(directions){
        apply(optionsBuilder)
        setPopUpTo(popUpToRoute, inclusive, saveState)
    }
}


/**
 * Navigate with the given [NavDirections] and pops the the backstack to the given [popUpToFragment].
 *
 * Only works if the route of each [NavDestination] is equal to the fully qualified name of the corresponding [Fragment].
 */
fun <T: Fragment> NavController.navigateAndPopUpTo(
    directions: NavDirections,
    popUpToFragment: KClass<T>,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    navigate(directions){
        apply(optionsBuilder)
        setPopUpTo(popUpToFragment.qualifiedName!!, inclusive, saveState)
    }
}


/**
 * Combination of [navigateIfNew] and [navigateAndPopUpTo].
 */
fun NavController.navigateIfNewAndPopUpTo(
    directions: NavDirections,
    @IdRes expectedCurrentDestination: Int,
    @IdRes popToDestination: Int,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(expectedCurrentDestination)) return

    navigateAndPopUpTo(
        directions,
        popToDestination,
        inclusive,
        saveState,
        optionsBuilder
    )
}


/**
 * Combination of [navigateIfNew] and [navigateAndPopUpTo].
 */
fun NavController.navigateIfNewAndPopUpTo(
    directions: NavDirections,
    expectedCurrentRoute: String,
    @IdRes popToDestination: Int,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(expectedCurrentRoute)) return

    navigateAndPopUpTo(
        directions,
        popToDestination,
        inclusive,
        saveState,
        optionsBuilder
    )
}


/**
 * Combination of [navigateIfNew] and [navigateAndPopUpTo].
 *
 * Only works if the route of each [NavDestination] is equal to the fully qualified name of the corresponding [Fragment].
 */
fun <T: Fragment> NavController.navigateIfNewAndPopUpTo(
    directions: NavDirections,
    expectedCurrentFragment: KClass<T>,
    @IdRes popToDestination: Int,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(expectedCurrentFragment)) return

    navigateAndPopUpTo(
        directions,
        popToDestination,
        inclusive,
        saveState,
        optionsBuilder
    )
}