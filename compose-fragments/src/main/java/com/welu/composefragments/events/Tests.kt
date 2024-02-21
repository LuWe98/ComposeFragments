package com.welu.composefragments.events

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.welu.composefragments.provider.NavBackStackEntryProvider
import com.welu.composefragments.result.FragmentResult
import com.welu.composefragments.result.IntResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.reflect.KClass


fun interface FragmentResultEventProvider : DispatchableEventProvider<FragmentResultEvent> {

    companion object {
        operator fun invoke(result: FragmentResult) = FragmentResultEventProvider {
            FragmentResultEvent(result)
        }

        operator fun invoke(key: String, result: FragmentResult) = FragmentResultEventProvider {
            FragmentResultEvent(key, result)
        }

        operator fun invoke(result: FragmentResult, @IdRes destinationId: Int) = FragmentResultEventProvider {
            FragmentResultEvent(result, destinationId)
        }

        operator fun invoke(key: String, result: FragmentResult, @IdRes destinationId: Int) = FragmentResultEventProvider {
            FragmentResultEvent(key, result, destinationId)
        }

        operator fun invoke(result: FragmentResult, fragmentClass: KClass<Fragment>) = FragmentResultEventProvider {
            FragmentResultEvent(result, fragmentClass)
        }

        operator fun invoke(key: String, result: FragmentResult, fragmentClass: KClass<Fragment>) = FragmentResultEventProvider {
            FragmentResultEvent(key, result, fragmentClass)
        }

        operator fun invoke(result: FragmentResult, provider: NavBackStackEntryProvider) = FragmentResultEventProvider {
            FragmentResultEvent(result, provider)
        }
    }
}

object A {

    fun fragmentResult(result: FragmentResult) = FragmentResultEventProvider(result)

    fun dispatch(vararg provider: DispatchableEventProvider<DispatchableEvent>) {

    }

    fun a() {
        val dispatcher = object : EventDispatcher<DispatchableEvent> {
            override val events: Flow<DispatchableEvent> = flow {  }

            override suspend fun dispatch(vararg events: DispatchableEvent) {
                TODO("Not yet implemented")
            }

            override fun autonomousDispatch(vararg events: DispatchableEvent) {
                TODO("Not yet implemented")
            }
        }

        dispatcher.autonomousDispatch(
            fragmentResult(IntResult(2)),
            FragmentResultEventProvider("key", IntResult(2))
        )
    }
}