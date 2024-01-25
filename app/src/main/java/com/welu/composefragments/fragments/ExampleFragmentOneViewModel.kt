package com.welu.composefragments.fragments

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.welu.composefragments.result.IntResult

class ExampleFragmentOneViewModel(
    private val safeStateHandle: SavedStateHandle
): ViewModel() {

    val flow = safeStateHandle.getStateFlow<IntResult?>("Bolonese", null)


    fun checkSafeStateHandle() {
        Log.d("ManualLog", "Instance: $safeStateHandle")
        safeStateHandle.keys().forEach {
            Log.d("ManualLog", "Key: $it")
        }
    }
}