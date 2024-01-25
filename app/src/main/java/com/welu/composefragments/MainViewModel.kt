package com.welu.composefragments

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welu.composefragments.result.IntResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val safeStateHandle: SavedStateHandle
): ViewModel() {

    private val flow = safeStateHandle.getStateFlow<IntResult?>("Bolonese", null)



    private val isInDarkThemeMutableStateFlow = MutableStateFlow(false)
    val useDarkThemeFlow = isInDarkThemeMutableStateFlow.asStateFlow()

    private val areDynamicColorsEnabledMutableStateFlow = MutableStateFlow(false)
    val useDynamicColorsFlow = areDynamicColorsEnabledMutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
//            delay(3000)
//            isInDarkThemeMutableStateFlow.update {
//                true
//            }
//
//            delay(3000)
//            areDynamicColorsEnabledMutableStateFlow.update {
//                true
//            }
        }
    }




}