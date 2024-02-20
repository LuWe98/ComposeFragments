package com.welu.composefragments.result

import android.os.Parcelable
import kotlin.reflect.KClass

/**
 * Can be wrapped inside of an UiEvent which can be dispatched to and processed by the MainActivity of the Application.
 */
interface FragmentResult : Parcelable {
    companion object {
        private const val FRAGMENT_RESULT_KEY_SUFFIX = "FragmentResultKey"

        fun <ResultType : FragmentResult> createResultKey(
            clazz: KClass<ResultType>
        ): String = FRAGMENT_RESULT_KEY_SUFFIX
            .plus("[")
            .plus(clazz.java.name)
            .plus("]")
    }
}

/**
 * Represents a default [FragmentResult] implementation which wraps its actual content inside a [value] property
 */
sealed interface WrappedFragmentResult<WrappedType>: FragmentResult {
    val value: WrappedType
}