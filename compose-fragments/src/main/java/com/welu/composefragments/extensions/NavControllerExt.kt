package com.welu.composefragments.extensions

import androidx.annotation.IdRes
import androidx.collection.valueIterator
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
 * Returns either the topmost [NavBackStackEntry] for a [Fragment] or null, if there is none.
 */
fun <T: Fragment> NavController.findBackStackEntry(fragment: T): NavBackStackEntry? = findBackStackEntry(fragment::class)

/**
 * Returns either the topmost [NavBackStackEntry] for a [Fragment] class or null, if there is none.
 */
inline fun <reified T: Fragment> NavController.findBackStackEntry(): NavBackStackEntry? = findBackStackEntry(T::class)


/**
 * Returns either the topmost [NavBackStackEntry] for a [Fragment] class or null, if there is none.
 */
fun <T: Fragment> NavController.findBackStackEntry(fragment: KClass<T>): NavBackStackEntry? {
    for(destination in graph.nodes.valueIterator()) {
        if (destination.getFragmentClassName() == fragment.qualifiedName && fragment.qualifiedName != null){
            return findBackStackEntry(destination.id)
        }
    }
    return null
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
 * Checks if the given [Fragment] is the topmost [NavDestination]
 */
inline fun <reified T : Fragment> NavController.isCurrentDestination(): Boolean = isCurrentDestination(T::class)


/**
 * Checks if the given [Fragment] [KClass] is the topmost [NavDestination]
 */
fun <T : Fragment> NavController.isCurrentDestination(fragmentClass: KClass<T>): Boolean = currentDestination.isDestinationOf(fragmentClass)


/**
 * Checks if the given [Fragment] is the previous [NavDestination]
 */
fun <T : Fragment> NavController.isPreviousDestination(fragment: T): Boolean = isPreviousDestination(fragment::class)

/**
 * Checks if the given [Fragment] is the previous [NavDestination]
 */
inline fun <reified T : Fragment> NavController.isPreviousDestination(): Boolean = isPreviousDestination(T::class)

/**
 * Checks if the given [Fragment] [KClass] is the previous [NavDestination]
 */
fun <T : Fragment> NavController.isPreviousDestination(fragmentClass: KClass<T>): Boolean = previousDestination.isDestinationOf(fragmentClass)


/**
 * Returns the topmost [NavBackStackEntry] for a [Fragment].
 */
fun <T: Fragment> NavController.getBackStackEntry(fragment: T): NavBackStackEntry = getBackStackEntry(fragment::class)

/**
 * Returns the topmost [NavBackStackEntry] for a [Fragment].
 */
inline fun <reified T: Fragment> NavController.getBackStackEntry(): NavBackStackEntry = getBackStackEntry(T::class)

/**
 * Returns the topmost [NavBackStackEntry] for a [Fragment] class.
 */
fun <T: Fragment> NavController.getBackStackEntry(fragmentClass: KClass<T>): NavBackStackEntry = findBackStackEntry(fragmentClass)!!


/**
 * Checks if the given [Fragment] is on the [NavController] backstack.
 */
fun <T: Fragment> NavController.isOnBackStack(fragment: T): Boolean = isOnBackStack(fragment::class)

/**
 * Checks if the given [Fragment] is on the [NavController] backstack.
 */
inline fun <reified T: Fragment> NavController.isOnBackStack(): Boolean = isOnBackStack(T::class)

/**
 * Checks if the given [Fragment] class is on the [NavController] backstack.
 */
fun <T: Fragment> NavController.isOnBackStack(fragmentClass: KClass<T>): Boolean = findBackStackEntry(fragmentClass) != null



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
 * Navigate with the given [NavDirections] and [NavOptions.Builder] if the [NavController.currentDestinationId] is equal to the expected [requiredDestination].
 */
fun NavController.navigateIfNew(
    directions: NavDirections,
    @IdRes requiredDestination: Int,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(requiredDestination)) return
    navigate(directions, optionsBuilder)
}


/**
 * Navigate with the given [NavDirections] and [NavOptions.Builder] if the [NavController.currentDestinationRoute] is equal to the expected [requiredRoute].
 */
fun NavController.navigateIfNew(
    directions: NavDirections,
    requiredRoute: String,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(requiredRoute)) return
    navigate(directions, optionsBuilder)
}

/**
 * Navigate with the given [NavDirections] and [NavOptions.Builder] if the [NavController.currentDestinationFragmentClassName] is equal to the expected [requiredFragment].
 */
fun <T: Fragment> NavController.navigateIfNew(
    directions: NavDirections,
    requiredFragment: KClass<T>,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(requiredFragment)) return
    navigate(directions, optionsBuilder)
}

/**
 * Navigate with the given [NavDirections] and [NavOptions.Builder] if the [NavController.currentDestinationFragmentClassName] is equal to the expected [expectedCurrentFragment].
 */
inline fun <reified T: Fragment> NavController.navigateIfNew(
    directions: NavDirections,
    noinline optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(T::class)) return
    navigate(directions, optionsBuilder)
}


/**
 * Navigate with the given [NavDirections] and pops the the backstack to the given [popUpToDestination].
 */
fun NavController.navigateAndPopUpTo(
    directions: NavDirections,
    @IdRes popToDestination: Int,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    navigate(directions){
        apply(optionsBuilder)
        setPopUpTo(popToDestination, inclusive, saveState)
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
 */
fun <T: Fragment> NavController.navigateAndPopUpTo(
    directions: NavDirections,
    popUpToFragment: KClass<T>,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    val backStackEntry = findBackStackEntry(popUpToFragment) ?: return

    navigate(directions){
        apply(optionsBuilder)
        setPopUpTo(backStackEntry.destination.id, inclusive, saveState)
    }
}

/**
 * See [navigateAndPopUpTo] with popUpToFragment.
 */
inline fun <reified T: Fragment> NavController.navigateAndPopUpTo(
    directions: NavDirections,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    noinline optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    navigateAndPopUpTo(
        directions = directions,
        popUpToFragment = T::class,
        inclusive = inclusive,
        saveState = saveState,
        optionsBuilder = optionsBuilder
    )
}


/**
 * Combination of [navigateIfNew] and [navigateAndPopUpTo].
 */
fun NavController.navigateIfNewAndPopUpTo(
    directions: NavDirections,
    @IdRes requiredDestination: Int,
    @IdRes popUpToDestination: Int,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(requiredDestination)) return

    navigateAndPopUpTo(
        directions = directions,
        popToDestination = popUpToDestination,
        inclusive = inclusive,
        saveState = saveState,
        optionsBuilder = optionsBuilder
    )
}


/**
 * Combination of [navigateIfNew] and [navigateAndPopUpTo].
 */
fun NavController.navigateIfNewAndPopUpTo(
    directions: NavDirections,
    requiredRoute: String,
    @IdRes popUpToDestination: Int,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(requiredRoute)) return

    navigateAndPopUpTo(
        directions = directions,
        popToDestination = popUpToDestination,
        inclusive = inclusive,
        saveState = saveState,
        optionsBuilder = optionsBuilder
    )
}

/**
 * Combination of [navigateIfNew] and [navigateAndPopUpTo].
 */
fun <T: Fragment> NavController.navigateIfNewAndPopUpTo(
    directions: NavDirections,
    requiredFragment: KClass<T>,
    @IdRes popUpToDestination: Int,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    if (!isCurrentDestination(requiredFragment)) return

    navigateAndPopUpTo(
        directions = directions,
        popToDestination = popUpToDestination,
        inclusive = inclusive,
        saveState = saveState,
        optionsBuilder = optionsBuilder
    )
}

/**
 * Combination of [navigateIfNew] and [navigateAndPopUpTo].
 */
inline fun <reified T: Fragment> NavController.navigateIfNewAndPopUpTo(
    directions: NavDirections,
    @IdRes popUpToDestination: Int,
    inclusive: Boolean = true,
    saveState: Boolean = false,
    noinline optionsBuilder: NavOptions.Builder.() -> Unit = {}
) {
    navigateIfNewAndPopUpTo(
        directions = directions,
        requiredFragment = T::class,
        popUpToDestination = popUpToDestination,
        inclusive = inclusive,
        saveState = saveState,
        optionsBuilder = optionsBuilder
    )
}