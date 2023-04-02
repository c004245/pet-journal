package com.example.pet_growth_journal

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.pet_growth_journal.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)

        window.statusBarColor = Color.TRANSPARENT

        initObserver()
    }

    private fun initObserver() {
        mainViewModel.currentView.observe(this) { viewMode ->
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
            navHostFragment.navController.navigate(viewMode.navigationId)
        }
    }

}

enum class MainViewMode(val navigationId: Int) {
    DAILY(R.id.navigation_daily_grow),
    ADD(R.id.navigation_add),
    TOTAL(R.id.navigation_total);

}