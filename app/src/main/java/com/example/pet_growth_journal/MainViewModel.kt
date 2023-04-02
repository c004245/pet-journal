package com.example.pet_growth_journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel() {

    private val _currentView = MutableLiveData(MainViewMode.DAILY)
    val currentView: LiveData<MainViewMode>
        get() = _currentView

    fun setCurrentView(view: MainViewMode) {
        _currentView.value = view
    }
}