package com.welu.composefragments.events.navigation

import androidx.navigation.NavController
import com.welu.composefragments.ComposeActivity

/**
 * A [ParameterizedNavigationEvent] can hold any number of parameter which are required to execute the navigation to another screen.
 */
class ParameterizedNavigationEvent(
    parameters: List<*>,
    navAction: NavController.(ComposeActivity, List<*>) -> Unit
) : NavigationEvent({
    navAction(it, parameters)
})


/**
 * The parsed [navAction] has to be completely autonomous.
 *
 * The only parameter this [navAction] can depend on is the provided parameter.
 */
fun <T> navEvent(
    parameterOne: T,
    navAction:  NavController.(ComposeActivity, T) -> Unit
) = ParameterizedNavigationEvent(
    parameters = listOf(
        parameterOne
    ),
    navAction = { activity, parameters ->
        @Suppress("UNCHECKED_CAST")
        navAction(activity, parameters[0] as T)
    }
)


/**
 * The parsed [navAction] has to be completely autonomous.
 *
 * The only parameter this [navAction] can depend on are the provided parameters in the function call.
 */
fun <T, T2> navEvent(
    parameterOne: T,
    parameterTwo: T2,
    navAction:  NavController.(ComposeActivity, T, T2) -> Unit
) = ParameterizedNavigationEvent(
    parameters = listOf(
        parameterOne,
        parameterTwo
    ),
    navAction = { activity, parameters ->
        @Suppress("UNCHECKED_CAST")
        navAction(
            activity,
            parameters[0] as T,
            parameters[1] as T2
        )
    }
)


/**
 * The parsed [navAction] has to be completely autonomous.
 *
 * The only parameter this [navAction] can depend on are the provided parameters in the function call.
 */
fun <T, T2, T3> navEvent(
    parameterOne: T,
    parameterTwo: T2,
    parameterThree: T3,
    navAction:  NavController.(ComposeActivity, T, T2, T3) -> Unit
) = ParameterizedNavigationEvent(
    parameters = listOf(
        parameterOne,
        parameterTwo,
        parameterThree
    ),
    navAction = { activity, parameters ->
        @Suppress("UNCHECKED_CAST")
        navAction(
            activity,
            parameters[0] as T,
            parameters[1] as T2,
            parameters[2] as T3
        )
    }
)

/**
 * The parsed [navAction] has to be completely autonomous.
 *
 * The only parameter this [navAction] can depend on are the provided parameters in the function call.
 */
fun <T, T2, T3, T4> navEvent(
    parameterOne: T,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    navAction:  NavController.(ComposeActivity, T, T2, T3, T4) -> Unit
) = ParameterizedNavigationEvent(
    parameters = listOf(
        parameterOne,
        parameterTwo,
        parameterThree,
        parameterFour
    ),
    navAction = { activity, parameters ->
        @Suppress("UNCHECKED_CAST")
        navAction(
            activity,
            parameters[0] as T,
            parameters[1] as T2,
            parameters[2] as T3,
            parameters[3] as T4
        )
    }
)


/**
 * The parsed [navAction] has to be completely autonomous.
 *
 * The only parameter this [navAction] can depend on are the provided parameters in the function call.
 */
fun <T, T2, T3, T4, T5> navEvent(
    parameterOne: T,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    navAction:  NavController.(ComposeActivity, T, T2, T3, T4, T5) -> Unit
) = ParameterizedNavigationEvent(
    parameters = listOf(
        parameterOne,
        parameterTwo,
        parameterThree,
        parameterFour,
        parameterFive
    ),
    navAction = { activity, parameters ->
        @Suppress("UNCHECKED_CAST")
        navAction(
            activity,
            parameters[0] as T,
            parameters[1] as T2,
            parameters[2] as T3,
            parameters[3] as T4,
            parameters[4] as T5
        )
    }
)

/**
 * The parsed [navAction] has to be completely autonomous.
 *
 * The only parameter this [navAction] can depend on are the provided parameters in the function call.
 */
fun <T, T2, T3, T4, T5, T6> navEvent(
    parameterOne: T,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    parameterSix: T6,
    navAction:  NavController.(ComposeActivity, T, T2, T3, T4, T5, T6) -> Unit
) = ParameterizedNavigationEvent(
    parameters = listOf(
        parameterOne,
        parameterTwo,
        parameterThree,
        parameterFour,
        parameterFive,
        parameterSix
    ),
    navAction = { activity, parameters ->
        @Suppress("UNCHECKED_CAST")
        navAction(
            activity,
            parameters[0] as T,
            parameters[1] as T2,
            parameters[2] as T3,
            parameters[3] as T4,
            parameters[4] as T5,
            parameters[5] as T6
        )
    }
)

/**
 * The parsed [navAction] has to be completely autonomous.
 *
 * The only parameter this [navAction] can depend on are the provided parameters in the function call.
 */
fun <T, T2, T3, T4, T5, T6, T7> navEvent(
    parameterOne: T,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    parameterSix: T6,
    parameterSeven: T7,
    navAction:  NavController.(ComposeActivity, T, T2, T3, T4, T5, T6, T7) -> Unit
) = ParameterizedNavigationEvent(
    parameters = listOf(
        parameterOne,
        parameterTwo,
        parameterThree,
        parameterFour,
        parameterFive,
        parameterSix,
        parameterSeven
    ),
    navAction = { activity, parameters ->
        @Suppress("UNCHECKED_CAST")
        navAction(
            activity,
            parameters[0] as T,
            parameters[1] as T2,
            parameters[2] as T3,
            parameters[3] as T4,
            parameters[4] as T5,
            parameters[5] as T6,
            parameters[6] as T7
        )
    }
)

/**
 * The parsed [navAction] has to be completely autonomous.
 *
 * The only parameter this [navAction] can depend on are the provided parameters in the function call.
 */
fun <T, T2, T3, T4, T5, T6, T7, T8> navEvent(
    parameterOne: T,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    parameterSix: T6,
    parameterSeven: T7,
    parameterEight: T8,
    navAction:  NavController.(ComposeActivity, T, T2, T3, T4, T5, T6, T7, T8) -> Unit
) = ParameterizedNavigationEvent(
    parameters = listOf(
        parameterOne,
        parameterTwo,
        parameterThree,
        parameterFour,
        parameterFive,
        parameterSix,
        parameterSeven,
        parameterEight
    ),
    navAction = { activity, parameters ->
        @Suppress("UNCHECKED_CAST")
        navAction(
            activity,
            parameters[0] as T,
            parameters[1] as T2,
            parameters[2] as T3,
            parameters[3] as T4,
            parameters[4] as T5,
            parameters[5] as T6,
            parameters[6] as T7,
            parameters[7] as T8
        )
    }
)

/**
 * The parsed [navAction] has to be completely autonomous.
 *
 * The only parameter this [navAction] can depend on are the provided parameters in the function call.
 */
fun <T, T2, T3, T4, T5, T6, T7, T8, T9> navEvent(
    parameterOne: T,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    parameterSix: T6,
    parameterSeven: T7,
    parameterEight: T8,
    parameterNine: T9,
    navAction:  NavController.(ComposeActivity, T, T2, T3, T4, T5, T6, T7, T8, T9) -> Unit
) = ParameterizedNavigationEvent(
    parameters = listOf(
        parameterOne,
        parameterTwo,
        parameterThree,
        parameterFour,
        parameterFive,
        parameterSix,
        parameterSeven,
        parameterEight,
        parameterNine
    ),
    navAction = { activity, parameters ->
        @Suppress("UNCHECKED_CAST")
        navAction(
            activity,
            parameters[0] as T,
            parameters[1] as T2,
            parameters[2] as T3,
            parameters[3] as T4,
            parameters[4] as T5,
            parameters[5] as T6,
            parameters[6] as T7,
            parameters[7] as T8,
            parameters[8] as T9
        )
    }
)


/**
 * The parsed [navAction] has to be completely autonomous.
 *
 * The only parameter this [navAction] can depend on are the provided parameters in the function call.
 */
fun <T, T2, T3, T4, T5, T6, T7, T8, T9, T10> navEvent(
    parameterOne: T,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    parameterSix: T6,
    parameterSeven: T7,
    parameterEight: T8,
    parameterNine: T9,
    parameterTen: T10,
    navAction:  NavController.(ComposeActivity, T, T2, T3, T4, T5, T6, T7, T8, T9, T10) -> Unit
) = ParameterizedNavigationEvent(
    parameters = listOf(
        parameterOne,
        parameterTwo,
        parameterThree,
        parameterFour,
        parameterFive,
        parameterSix,
        parameterSeven,
        parameterEight,
        parameterNine,
        parameterTen
    ),
    navAction = { activity, parameters ->
        @Suppress("UNCHECKED_CAST")
        navAction(
            activity,
            parameters[0] as T,
            parameters[1] as T2,
            parameters[2] as T3,
            parameters[3] as T4,
            parameters[4] as T5,
            parameters[5] as T6,
            parameters[6] as T7,
            parameters[7] as T8,
            parameters[8] as T9,
            parameters[9] as T10
        )
    }
)