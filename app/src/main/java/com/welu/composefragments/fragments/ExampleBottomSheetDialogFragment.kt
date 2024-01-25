package com.welu.composefragments.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.welu.composefragments.extensions.navController
import com.welu.composefragments.ui.theme.ComposeFragmentsTheme

class ExampleBottomSheetDialogFragment: ComposeBottomSheetDialogFragment() {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .fillMaxWidth()
        ) {
            Button(onClick = {
                navController.navigate(ExampleBottomSheetDialogFragmentDirections.toExampleDialogFragment())
            }) {
                Text(text = "Go to dialog 2")
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun Preview() {
        ComposeFragmentsTheme {
            Content()
        }
    }
}