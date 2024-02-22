package com.welu.composefragments.fragments.screenone

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.welu.composefragments.ComposeActivity
import com.welu.composefragments.events.base.DispatchableEvent
import com.welu.composefragments.events.base.EventDispatcher
import com.welu.composefragments.events.navigation.NavigationEvent
import com.welu.composefragments.events.navigation.navEvent
import com.welu.composefragments.result.IntResult
import kotlinx.coroutines.launch

class ExampleFragmentOneViewModel(
    private val safeStateHandle: SavedStateHandle
): ViewModel() {

    fun onButtonClicked(dispatcher: EventDispatcher<DispatchableEvent>) {

        viewModelScope.launch {
            var event: NavigationEvent? = navEvent {
                Log.d("manual", "HELLO FROM ACTION 2")
            }

            dispatcher.delegatingDispatch(event!!)

            event = null
            Log.d("manual", "EVENT IS NOW NULL - $event")
        }
    }
}