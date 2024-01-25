package com.welu.composefragments.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.welu.composefragments.extensions.navController

class ExampleFragmentTwo: ComposeFragment() {

    @Composable
    override fun Content() {
        Column {
            Text(text = "Fragment Two")
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Navigate back to FragmentOne")
            }
        }
    }

}