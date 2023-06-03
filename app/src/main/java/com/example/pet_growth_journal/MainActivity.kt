package com.example.pet_growth_journal

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.pet_growth_journal.databinding.ActivityMainBinding
import com.example.pet_growth_journal.result.EventObserver
import com.example.pet_growth_journal.ui.add.AddPopup
import com.example.pet_growth_journal.ui.common.DialogPopupFragmentManager
import com.example.pet_growth_journal.ui.common.PopupName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var addPopup: AddPopup
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

        mainViewModel.showAddPopup.observe(this) {
            if (!::addPopup.isInitialized) {
                addPopup = AddPopup(mainViewModel)
            }

            addPopup.startPopup(
                R.id.fl_main_activity_popup,
                DialogPopupFragmentManager(supportFragmentManager)
            )

            binding.flMainActivityPopup.visibility = View.VISIBLE
        }

        mainViewModel.closePopup.observe(this, EventObserver {
            when (it) {
                PopupName.ADD -> {
                    if (::addPopup.isInitialized) {
                        addPopup.closePopup()
                    }
                }
            }
            binding.flMainActivityPopup.visibility = View.GONE
        })
    }


}

enum class MainViewMode(val navigationId: Int) {
    DAILY(R.id.navigation_daily_grow),
//    ADD(R.id.navigation_add),
    TOTAL(R.id.navigation_total);

}