package com.welu.composefragments

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.welu.composefragments.databinding.ActivityLayoutBinding
import com.welu.composefragments.events.dispatchResult
import com.welu.composefragments.extensions.collectOnStarted
import com.welu.composefragments.result.IntResult
import com.welu.composefragments.ui.theme.ComposeFragmentsTheme
import kotlinx.coroutines.launch

//Testen, ob ein Navigation Event oder Result Event - mit callback funktioniert, wenn bspw. der viewmodel dann destroyed wird.
//-> Sonst müsste man ein Navigation event erstellen, welches als parameter die Werte nimmt und autonom ist, wie bei der QuizApp
class MainActivity : ComposeActivity() {

    val viewModel by viewModels<MainViewModel>()

    private val activityEventDispatcher = ActivityEventDispatcher()
    private val fragmentResultEventDispatcher = FragmentResultEventDispatcher(activityEventDispatcher)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

        setContent {
            WithTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AndroidViewBinding(
                        factory = ActivityLayoutBinding::inflate,
                        modifier = Modifier.fillMaxSize().statusBarsPadding()
                    )
                }
            }
        }
    }

    @Composable
    override fun WithTheme(content: @Composable () -> Unit) {
        val useDarkTheme by viewModel.useDarkThemeFlow.collectAsState()
        val useDynamicColors by viewModel.useDynamicColorsFlow.collectAsState()

        ComposeFragmentsTheme(
            darkTheme = true,
            dynamicColor = useDynamicColors,
            content = content
        )
    }

}