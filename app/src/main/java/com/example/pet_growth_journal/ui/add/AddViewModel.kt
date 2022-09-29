package com.example.pet_growth_journal.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddViewModel : ViewModel() {

    private var _currentType = MutableLiveData<CurrentType>(CurrentType.PICTURE)
    val currentType: LiveData<CurrentType>
        get() = _currentType


}

enum class CurrentType {
    PICTURE, CATEGORY, EMOTION
}