package com.welu.composefragments.events

import androidx.annotation.IdRes
import com.welu.composefragments.provider.NavBackStackEntryProvider
import com.welu.composefragments.result.FragmentResult

class FragmentResultEvent(
    val key: String,
    val result: FragmentResult,
    val provider: NavBackStackEntryProvider
) : DispatchableEvent {

    constructor(
        result: FragmentResult
    ) : this(
        key = FragmentResult.createResultKey(result::class),
        result = result
    )

    constructor(
        key: String,
        result: FragmentResult
    ) : this(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Previous
    )

    constructor(
        result: FragmentResult,
        @IdRes destinationId: Int
    ) : this(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        destinationId = destinationId
    )

    constructor(
        key: String,
        result: FragmentResult,
        @IdRes destinationId: Int
    ) : this(
        key = key,
        result = result,
        provider = NavBackStackEntryProvider.Id(destinationId)
    )

    constructor(
        result: FragmentResult,
        provider: NavBackStackEntryProvider
    ) : this(
        key = FragmentResult.createResultKey(result::class),
        result = result,
        provider = provider
    )
}