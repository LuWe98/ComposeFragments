package com.welu.composefragments

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.welu.composefragments.events.base.DispatchableEvent
import com.welu.composefragments.events.base.DispatchableEventBatch
import com.welu.composefragments.events.base.EventDispatcher
import com.welu.composefragments.events.fragmentresult.dispatch
import com.welu.composefragments.events.navigation.NavigationEvent
import com.welu.composefragments.example.databinding.ActivityLayoutBinding
import com.welu.composefragments.extensions.collectOnStarted
import com.welu.composefragments.extensions.navController
import com.welu.composefragments.result.IntResult
import com.welu.composefragments.ui.theme.ComposeFragmentsTheme
import kotlinx.coroutines.launch

//Testen, ob ein Navigation Event oder Result Event - mit callback funktioniert, wenn bspw. der viewmodel dann destroyed wird.
//-> Sonst müsste man ein Navigation event erstellen, welches als parameter die Werte nimmt und autonom ist, wie bei der QuizApp
class MainActivity : ComposeActivity() {

    val viewModel by viewModels<MainViewModel>()
    lateinit var binding: ActivityLayoutBinding

    val activityEventDispatcher: EventDispatcher<DispatchableEvent> = ActivityEventDispatcher(lifecycleScope)

    val fragmentResultEventDispatcher = FragmentResultEventDispatcher(
        activityEventDispatcher,
        lifecycleScope
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)


        lifecycleScope.launch {
            fragmentResultEventDispatcher.dispatch(IntResult(2))
            activityEventDispatcher.dispatch("", IntResult(3))
        }

        initView()

        activityEventDispatcher.events.collectOnStarted(this) {
            executeEvent(it)
        }
    }

    private fun executeEvent(event: DispatchableEvent) {
        Log.d("manual", "[EVENT]: $event")

        when(event) {
            is NavigationEvent -> event.execute(this)
            is DispatchableEventBatch -> event.events.forEach(::executeEvent)
        }
    }

    private fun initView() {
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        navController.graph = navController.navInflater.inflate(com.welu.composefragments.example.R.navigation.main_nav_graph).apply {
           setStartDestination(com.welu.composefragments.example.R.id.exampleFragmentOne)
        }
        setContentView(binding.root)
    }


    @Composable
    override fun ProvideTheme(content: @Composable () -> Unit) {
        val useDarkTheme by viewModel.useDarkThemeFlow.collectAsStateWithLifecycle()
        val useDynamicColors by viewModel.useDynamicColorsFlow.collectAsStateWithLifecycle()

        ComposeFragmentsTheme(
            darkTheme = useDarkTheme,
            dynamicColor = useDynamicColors,
            content = content
        )
    }
}