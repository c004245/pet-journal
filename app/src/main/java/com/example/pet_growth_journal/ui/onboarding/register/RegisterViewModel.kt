package com.example.pet_growth_journal.ui.onboarding.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

): ViewModel() {


    private var _nameEnable = MutableLiveData(false)
    val nameEnable: LiveData<Boolean>
        get() = _nameEnable
    fun setNameState(name: String) {
        _nameEnable.value = name.isNotEmpty()
    }
}