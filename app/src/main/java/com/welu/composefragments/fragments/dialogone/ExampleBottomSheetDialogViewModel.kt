package com.welu.composefragments.fragments.dialogone

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welu.composefragments.R
import com.welu.composefragments.events.base.DispatchableEvent
import com.welu.composefragments.events.base.EventDispatcher
import com.welu.composefragments.events.base.batchDispatch
import com.welu.composefragments.events.navigation.NavigationEvent
import com.welu.composefragments.events.navigation.navEvent
import com.welu.composefragments.extensions.popBackStack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Test {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
}

class ExampleBottomSheetDialogViewModel: ViewModel() {

    fun onButtonClicked(dispatcher: EventDispatcher<DispatchableEvent>) {
        viewModelScope.launch {
            var event: NavigationEvent? = navEvent("Hello") { _, p ->
                //popBackStack(R.id.exampleBottomSheetDialogFragment, true)
                popBackStack(ExampleBottomSheetDialogFragment::class)
                Log.d("manual", "HELLO FROM Back action- : $p")
            }

            var otherEvent: NavigationEvent? = navEvent(23) { _, number ->
//                navigate(R.id.exampleDialogFragment)
                Log.d("manual", "HELLO FROM Navigation Action - $number")
            }

            dispatcher.dispatch(
                event!!,
                otherEvent!!
            )

            event = null
            otherEvent = null
            Log.d("manual", "EVENT IS NOW NULL - $event - $otherEvent")
        }
    }

    fun onButtonClickedTest() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("manual", "Start")

            Test.scope.launch {
                Log.d("manual", "Start Test")
                delay(3000)
                Log.d("manual", "End Test")
            }

            delay(3000)
            Log.d("manual", "End")
        }.invokeOnCompletion {
            Log.d("manual", "Cancel")
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("manual", "On Cleared")
    }
}