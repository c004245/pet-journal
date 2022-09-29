package com.example.pet_growth_journal

import android.app.Application
import androidx.navigation.NavDestination
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {

    companion object {
        var currentFragment = ""
        var currentDirection: NavDestination? = null

    }
}