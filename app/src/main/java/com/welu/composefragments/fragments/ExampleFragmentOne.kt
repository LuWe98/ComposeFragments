package com.welu.composefragments.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.welu.composefragments.R
import com.welu.composefragments.extensions.collectOnStarted
import com.welu.composefragments.extensions.navController
import com.welu.composefragments.result.FragmentResultCollector
import com.welu.composefragments.result.IntResult
import com.welu.composefragments.ui.theme.ComposeFragmentsTheme
import kotlinx.coroutines.flow.emptyFlow

class ExampleFragmentOne : ComposeFragment() {

    //val viewModel by viewModels<ExampleFragmentOneViewModel>()

    @Composable
    override fun Content() {
        var currentValue by remember {
            mutableStateOf<Int>(0)
        }

        //val a = R.id.exampleFragmentOne

//        FragmentResultCollector<IntResult>(com.welu.composefragments.R.id.exampleFragmentOne){
//            currentValue = it.value
//        }
//
//        Column {
//            Button(onClick = {
//                navController.navigate(ExampleFragmentOneDirections.toExampleBottomSheetDialogFragment())
//            }) {
//                Text(text = "Go to FragmentTwo")
//            }
//
//            Text(text = "ParsedValue: $currentValue")
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    @Preview(showBackground = true)
    @Composable
    private fun Preview() {
        ComposeFragmentsTheme {
            Content()
        }
    }
}