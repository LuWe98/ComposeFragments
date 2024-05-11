package com.welu.composefragments.fragments.screentwo

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.welu.composefragments.extensions.navController
import com.welu.composefragments.fragments.ComposeFragment

class ExampleFragmentTwo: ComposeFragment() {

    override val applyStatusBarPadding: Boolean = true

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