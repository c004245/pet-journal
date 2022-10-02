package com.example.pet_growth_journal.ui.dailygrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyGrowViewModel @Inject constructor() : ViewModel() {
    private val _isTodayRecord = MutableLiveData<Boolean>(false)
    val isTodayRecord: LiveData<Boolean>
        get() = _isTodayRecord


}

data class CommonDailyGrowModel(
    val commonModel: List<DailyGrowModel>? = null
)

data class DailyGrowModel(
    val id: Int,
    val thumbnail: String,
    val title: String,
    val time: String,
    val memo: String?= null,
    val category: Category,
    val emotion: Emotion,
    val weather: Weather
)

enum class Category {
    WALK, FEED, SNACK, WATER, EVENT, OUTSIDE, INSIDE, HOSPITAL, MEDICINE, GARGLE, BATH, PUPU, TRAINING, SLEEP, WAKE
}

enum class Emotion {
    HAPPY, SAD,
}

enum class Weather {
    SUNNY, RAINY
}