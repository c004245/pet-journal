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

    private var _moreEnable = MutableLiveData(false)
    val moreEnable: LiveData<Boolean>
        get() = _moreEnable

    private var _pictureEnable = MutableLiveData(false)
    val pictureEnable: LiveData<Boolean>
        get() = _pictureEnable

    private var _genderType = MutableLiveData(PetGender.NONE)
    val genderType: LiveData<PetGender>
        get() = _genderType

    fun setNameState(name: String) {
        _nameEnable.value = name.isNotEmpty()
    }
    fun setMoreState(isEnable: Boolean) {
        _moreEnable.value = isEnable
    }

    fun clickGender(gender: PetGender) {
        _genderType.value = gender
    }
}

enum class PetGender {
    NONE, MALE, FEMALE
}