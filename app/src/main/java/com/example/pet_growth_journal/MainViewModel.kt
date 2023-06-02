package com.example.pet_growth_journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pet_growth_journal.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel() {

    private val _currentView = MutableLiveData(MainViewMode.DAILY)
    val currentView: LiveData<MainViewMode>
        get() = _currentView

    private val _showAddPopup = SingleLiveEvent<Unit>()
    val showAddPopup: LiveData<Unit>
        get() = _showAddPopup

    fun setCurrentView(view: MainViewMode) {
        _currentView.value = view
    }

    fun showAddPopup() {
        _showAddPopup.value = Unit
    }
}