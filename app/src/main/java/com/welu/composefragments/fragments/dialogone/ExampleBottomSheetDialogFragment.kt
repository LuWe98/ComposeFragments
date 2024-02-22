package com.welu.composefragments.fragments.dialogone

import android.os.Bundle
import android.util.Log
import android.view.View
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
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.welu.composefragments.MainActivity
import com.welu.composefragments.R
import com.welu.composefragments.extensions.isCurrentDestination
import com.welu.composefragments.extensions.isOnBackStack
import com.welu.composefragments.extensions.navController
import com.welu.composefragments.fragments.ComposeBottomSheetDialogFragment
import com.welu.composefragments.fragments.dialogtwo.ExampleDialogFragment
import com.welu.composefragments.ui.theme.ComposeFragmentsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExampleBottomSheetDialogFragment: ComposeBottomSheetDialogFragment() {

    private val viewModel by viewModels<ExampleBottomSheetDialogViewModel>()

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .fillMaxWidth()
        ) {
            Button(onClick = {
//                lifecycleScope.launch {
//                    viewModel.onButtonClickedTest()
//                    delay(1000)
//                    navController.popBackStack(R.id.exampleBottomSheetDialogFragment, true)
//                }

                viewModel.onButtonClicked((composeActivity as MainActivity).activityEventDispatcher)
                //navController.navigate(R.id.exampleDialogFragment)
                //navController.navigate(ExampleBottomSheetDialogFragmentDirections.toExampleDialogFragment())
            }) {
                Text(text = "Go to dialog 2")
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text(text = "Other Text")

            Spacer(modifier = Modifier.height(50.dp))

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skipHalfExpandedAndCollapsing()

        val isCurrent = navController.isCurrentDestination(ExampleBottomSheetDialogFragment::class)
        val onBackStack = navController.isOnBackStack(ExampleDialogFragment::class)

        Log.d("Manual", "OnBackstack?: $onBackStack - IsCurrent: $isCurrent")
    }

    @Preview(showBackground = true)
    @Composable
    private fun Preview() {
        ComposeFragmentsTheme {
            Content()
        }
    }
}

/*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val composeActivity = this.composeActivity

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                -> The WindowInsets.current returns the insets of the LocalView.current.
                -> The insets of a BottomSheetDialogFragment are applied to the decorView of the dialog window.
                -> In order to fix the WindowInsets, the LocalView has to return the the dialog decorView
                CompositionLocalProvider(LocalView provides dialog!!.window!!.decorView) {
                    composeActivity.WithTheme {
                        composeActivity.WithBottomSheetDialogFragmentSurface {

                        }
                    }
                }

            }
        }
    }
 */