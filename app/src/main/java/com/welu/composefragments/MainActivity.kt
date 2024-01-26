package com.welu.composefragments

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import com.welu.composefragments.databinding.ActivityLayoutBinding
import com.welu.composefragments.ui.theme.ComposeFragmentsTheme

//Testen, ob ein Navigation Event oder Result Event - mit callback funktioniert, wenn bspw. der viewmodel dann destroyed wird.
//-> Sonst müsste man ein Navigation event erstellen, welches als parameter die Werte nimmt und autonom ist, wie bei der QuizApp
class MainActivity : ComposeActivity() {

    val viewModel by viewModels<MainViewModel>()
    lateinit var binding: ActivityLayoutBinding

    private val activityEventDispatcher = ActivityEventDispatcher()
    private val fragmentResultEventDispatcher = FragmentResultEventDispatcher(activityEventDispatcher)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

// This lead to problematic view inflation in the child fragments
//        setContent {
//            WithTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
////                    AndroidViewBinding(
////                        factory = ActivityLayoutBinding::inflate,
////                        modifier = Modifier.fillMaxSize().statusBarsPadding()
////                    )
//                }
//            }
//        }
    }

    @Composable
    override fun WithTheme(content: @Composable () -> Unit) {
        val useDarkTheme by viewModel.useDarkThemeFlow.collectAsState()
        val useDynamicColors by viewModel.useDynamicColorsFlow.collectAsState()

        ComposeFragmentsTheme(
            darkTheme = useDarkTheme,
            dynamicColor = useDynamicColors,
            content = content
        )
    }
}