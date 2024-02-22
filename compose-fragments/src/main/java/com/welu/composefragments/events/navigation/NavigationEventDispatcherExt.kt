package com.welu.composefragments.events.navigation

import androidx.navigation.NavController
import com.welu.composefragments.ComposeActivity
import com.welu.composefragments.events.base.EventDispatcher

suspend fun EventDispatcher<in NavigationEvent>.dispatch(
    navAction: NavController.(ComposeActivity) -> Unit
) {
    dispatch(navEvent(navAction))
}

suspend fun<T> EventDispatcher<in NavigationEvent>.dispatch(
    parameterOne: T,
    navAction: NavController.(ComposeActivity, T) -> Unit
) {
    dispatch(
        navEvent(
            parameterOne = parameterOne,
            navAction = navAction
        )
    )
}

suspend fun<T1, T2> EventDispatcher<in NavigationEvent>.dispatch(
    parameterOne: T1,
    parameterTwo: T2,
    navAction: NavController.(ComposeActivity, T1, T2) -> Unit
) {
    dispatch(
        navEvent(
            parameterOne = parameterOne,
            parameterTwo = parameterTwo,
            navAction = navAction
        )
    )
}

suspend fun<T1, T2, T3> EventDispatcher<in NavigationEvent>.dispatch(
    parameterOne: T1,
    parameterTwo: T2,
    parameterThree: T3,
    navAction: NavController.(ComposeActivity, T1, T2, T3) -> Unit
) {
    dispatch(
        navEvent(
            parameterOne = parameterOne,
            parameterTwo = parameterTwo,
            parameterThree = parameterThree,
            navAction = navAction
        )
    )
}

suspend fun<T1, T2, T3, T4> EventDispatcher<in NavigationEvent>.dispatch(
    parameterOne: T1,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    navAction: NavController.(ComposeActivity, T1, T2, T3, T4) -> Unit
) {
    dispatch(
        navEvent(
            parameterOne = parameterOne,
            parameterTwo = parameterTwo,
            parameterThree = parameterThree,
            parameterFour = parameterFour,
            navAction = navAction
        )
    )
}

suspend fun<T1, T2, T3, T4, T5> EventDispatcher<in NavigationEvent>.dispatch(
    parameterOne: T1,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    navAction: NavController.(ComposeActivity, T1, T2, T3, T4, T5) -> Unit
) {
    dispatch(
        navEvent(
            parameterOne = parameterOne,
            parameterTwo = parameterTwo,
            parameterThree = parameterThree,
            parameterFour = parameterFour,
            parameterFive = parameterFive,
            navAction = navAction
        )
    )
}

suspend fun<T1, T2, T3, T4, T5, T6> EventDispatcher<in NavigationEvent>.dispatch(
    parameterOne: T1,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    parameterSix: T6,
    navAction: NavController.(ComposeActivity, T1, T2, T3, T4, T5, T6) -> Unit
) {
    dispatch(
        navEvent(
            parameterOne = parameterOne,
            parameterTwo = parameterTwo,
            parameterThree = parameterThree,
            parameterFour = parameterFour,
            parameterFive = parameterFive,
            parameterSix = parameterSix,
            navAction = navAction
        )
    )
}

suspend fun<T1, T2, T3, T4, T5, T6, T7> EventDispatcher<in NavigationEvent>.dispatch(
    parameterOne: T1,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    parameterSix: T6,
    parameterSeven: T7,
    navAction: NavController.(ComposeActivity, T1, T2, T3, T4, T5, T6, T7) -> Unit
) {
    dispatch(
        navEvent(
            parameterOne = parameterOne,
            parameterTwo = parameterTwo,
            parameterThree = parameterThree,
            parameterFour = parameterFour,
            parameterFive = parameterFive,
            parameterSix = parameterSix,
            parameterSeven = parameterSeven,
            navAction = navAction
        )
    )
}

suspend fun<T1, T2, T3, T4, T5, T6, T7, T8> EventDispatcher<in NavigationEvent>.dispatch(
    parameterOne: T1,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    parameterSix: T6,
    parameterSeven: T7,
    parameterEight: T8,
    navAction: NavController.(ComposeActivity, T1, T2, T3, T4, T5, T6, T7, T8) -> Unit
) {
    dispatch(
        navEvent(
            parameterOne = parameterOne,
            parameterTwo = parameterTwo,
            parameterThree = parameterThree,
            parameterFour = parameterFour,
            parameterFive = parameterFive,
            parameterSix = parameterSix,
            parameterSeven = parameterSeven,
            parameterEight = parameterEight,
            navAction = navAction
        )
    )
}

suspend fun<T1, T2, T3, T4, T5, T6, T7, T8, T9> EventDispatcher<in NavigationEvent>.dispatch(
    parameterOne: T1,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    parameterSix: T6,
    parameterSeven: T7,
    parameterEight: T8,
    parameterNine: T9,
    navAction: NavController.(ComposeActivity, T1, T2, T3, T4, T5, T6, T7, T8, T9) -> Unit
) {
    dispatch(
        navEvent(
            parameterOne = parameterOne,
            parameterTwo = parameterTwo,
            parameterThree = parameterThree,
            parameterFour = parameterFour,
            parameterFive = parameterFive,
            parameterSix = parameterSix,
            parameterSeven = parameterSeven,
            parameterEight = parameterEight,
            parameterNine = parameterNine,
            navAction = navAction
        )
    )
}

suspend fun<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> EventDispatcher<in NavigationEvent>.dispatch(
    parameterOne: T1,
    parameterTwo: T2,
    parameterThree: T3,
    parameterFour: T4,
    parameterFive: T5,
    parameterSix: T6,
    parameterSeven: T7,
    parameterEight: T8,
    parameterNine: T9,
    parameterTen: T10,
    navAction: NavController.(ComposeActivity, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) -> Unit
) {
    dispatch(
        navEvent(
            parameterOne = parameterOne,
            parameterTwo = parameterTwo,
            parameterThree = parameterThree,
            parameterFour = parameterFour,
            parameterFive = parameterFive,
            parameterSix = parameterSix,
            parameterSeven = parameterSeven,
            parameterEight = parameterEight,
            parameterNine = parameterNine,
            parameterTen = parameterTen,
            navAction = navAction
        )
    )
}