package com.welu.composefragments.fragments

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.welu.composefragments.R
import com.welu.composefragments.composables.LocalFragment
import com.welu.composefragments.extensions.navController
import com.welu.composefragments.navigation.navOptions
import com.welu.composefragments.ui.theme.ComposeFragmentsTheme

class ExampleFragmentOne : ComposeFragment() {

    //val viewModel by viewModels<ExampleFragmentOneViewModel>()

    @Composable
    override fun Content() {

        val fragment = LocalFragment.current

        var currentValue by remember {
            mutableStateOf<Int>(0)
        }

        navOptions {
            setLaunchSingleTop(true)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(onClick = {
                Log.d("manual", "Fragment: $fragment")
                //navController.navigate(R.id.exampleBottomSheetDialogFragment)
            }) {
                Text(text = "Click me")
            }


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

    @Preview(showBackground = true)
    @Composable
    private fun Preview() {
        ComposeFragmentsTheme {
            Content()
        }
    }
}