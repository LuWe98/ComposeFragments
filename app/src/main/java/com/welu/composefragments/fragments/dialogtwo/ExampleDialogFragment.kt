package com.welu.composefragments.fragments.dialogtwo

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.welu.composefragments.extensions.isCurrentDestination
import com.welu.composefragments.extensions.navController
import com.welu.composefragments.fragments.ComposeDialogFragment
import com.welu.composefragments.fragments.screentwo.ExampleFragmentTwo
import com.welu.composefragments.result.IntResult
import com.welu.composefragments.result.sendFragmentResult

class ExampleDialogFragment : ComposeDialogFragment() {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(10.dp))
        ) {
            Button(onClick = {
                //navController.navigate(ExampleDialogFragmentDirections.toExampleFragmentTwo())
            }) {
                Text(text = "Navigate to Fragment Two")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                sendFragmentResult<ExampleFragmentTwo>(IntResult(2))
                //sendFragmentResult(IntResult(2))
                //sendFragmentResultTo(IntResult(2), R.id.exampleFragmentOne)
            }) {
                Text(text = "Send Result")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "DialogFragment")

            Spacer(modifier = Modifier.height(20.dp))

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController.isCurrentDestination(this::class)
    }

}