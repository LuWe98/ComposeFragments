package com.welu.composefragments.result

import android.os.Parcelable
import kotlin.reflect.KClass

/**
 * Can be wrapped inside of an UiEvent which can be dispatched to and processed by the MainActivity of the Application.
 *
 * Example FragmentResultEvent with FragmentManager:
 *
 *      class FragmentResultEvent(
 *          val result: FragmentResult,
 *          val fragmentManagerProvider: FragmentManagerProvider,
 *          val resultKey: String = FragmentResult.generateResultKey(result::class),
 *          val requestKey: String = resultKey,
 *      ) : UiEvent
 *
 *
 *  Example FragmentResultEvent with NavBackStackEntries:
 *
 *      class FragmentResultEvent(
 *          val key: String,
 *          val result: FragmentResult,
 *          val provider: NavBackStackEntryProvider
 *      ): UiEvent {
 *          constructor(result: FragmentResult): this(
 *              key = FragmentResult.generateResultKey(result::class),
 *              result = result,
 *              provider = NavBackStackEntryProvider.Previous
 *          )
 *
 *          constructor(key: String, result: FragmentResult): this(
 *              key = key,
 *              result = result,
 *              provider = NavBackStackEntryProvider.Previous
 *          )
 *
 *          constructor(result: FragmentResult, provider: NavBackStackEntryProvider): this(
 *              key = FragmentResult.generateResultKey(result::class),
 *              result = result,
 *              provider = provider
 *          )
 *      }
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