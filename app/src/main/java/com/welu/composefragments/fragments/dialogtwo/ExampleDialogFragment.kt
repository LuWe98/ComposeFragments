package com.welu.composefragments.fragments.dialogtwo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.welu.composefragments.fragments.ComposeDialogFragment

class ExampleDialogFragment : ComposeDialogFragment() {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.60f)
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(10.dp))
                .padding(32.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "DialogFragment")

            Spacer(modifier = Modifier.height(20.dp))

            TextField(value = "", onValueChange = {})
        }
    }
}