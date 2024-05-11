package com.welu.composefragments.fragments.screenone

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.welu.composefragments.MainActivity
import com.welu.composefragments.composables.LocalFragment
import com.welu.composefragments.events.navigation.navEvent
import com.welu.composefragments.fragments.ComposeFragment
import com.welu.composefragments.navigation.navOptions
import com.welu.composefragments.result.IntResult
import com.welu.composefragments.result.fragmentResultCollector
import com.welu.composefragments.ui.theme.ComposeFragmentsTheme

class ExampleFragmentOne : ComposeFragment() {

    val viewModel by viewModels<ExampleFragmentOneViewModel>()

    @Composable
    override fun Content() {

        val fragment = LocalFragment.current

        var currentValue by remember {
            mutableIntStateOf(0)
        }

        navOptions {
            setLaunchSingleTop(true)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(onClick = {
                (composeActivity as MainActivity).activityEventDispatcher.delegatingDispatch(navEvent {
                    navigate(com.welu.composefragments.example.R.id.exampleFragmentTwo)
                })
            }) {
                Text(text = "To FragmentTwo")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                (composeActivity as MainActivity).activityEventDispatcher.delegatingDispatch(navEvent {
                    navigate(com.welu.composefragments.example.R.id.exampleDialogFragment)
                })
            }) {
                Text(text = "To DialogFragment")
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentResultCollector<IntResult> {
            Log.d("manual", "Result: $it")
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