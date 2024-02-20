package com.welu.composefragments

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.welu.composefragments.databinding.ActivityLayoutBinding
import com.welu.composefragments.extensions.navController
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

        initView()
    }

    private fun initView() {
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        navController.graph = navController.navInflater.inflate(R.navigation.nav_graph).apply {
            setStartDestination(R.id.exampleFragmentOne)
        }
        setContentView(binding.root)
    }


    @Composable
    override fun WithTheme(content: @Composable () -> Unit) {
        val useDarkTheme by viewModel.useDarkThemeFlow.collectAsStateWithLifecycle()
        val useDynamicColors by viewModel.useDynamicColorsFlow.collectAsStateWithLifecycle()

        ComposeFragmentsTheme(
            darkTheme = useDarkTheme,
            dynamicColor = useDynamicColors,
            content = content
        )
    }
}