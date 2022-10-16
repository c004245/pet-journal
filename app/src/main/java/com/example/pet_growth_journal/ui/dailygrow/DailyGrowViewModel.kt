package com.example.pet_growth_journal.ui.dailygrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyGrowViewModel @Inject constructor() : ViewModel() {
    private val _isTodayRecord = MutableLiveData(true)
    val isTodayRecord: LiveData<Boolean>
        get() = _isTodayRecord

    private val _dailyGrowModel = MutableLiveData<List<DailyGrowModel>>()
    val dailyGrowModel: LiveData<List<DailyGrowModel>>
        get() = _dailyGrowModel


    init {
        _dailyGrowModel.value = listOf(
            DailyGrowModel(
                id = 0,
                thumbnail = "https://img.youtube.com/vi/6PlkYCfW0_U/0.jpg",
                title = "ㅌㅔ스트",
                time = "00",
                category = Category.BATH,
                emotion = Emotion.HAPPY,
                weather = Weather.RAINY
            ),
            DailyGrowModel(
                id = 0,
                thumbnail = "https://img.youtube.com/vi/6PlkYCfW0_U/0.jpg",
                title = "ㅌㅔ스트",
                time = "00",
                category = Category.BATH,
                emotion = Emotion.HAPPY,
                weather = Weather.RAINY
            ),
            DailyGrowModel(
                id = 0,
                thumbnail = "https://img.youtube.com/vi/6PlkYCfW0_U/0.jpg",
                title = "ㅌㅔ스트",
                time = "00",
                category = Category.BATH,
                emotion = Emotion.SAD,
                weather = Weather.RAINY
            ),
        )
    }
}
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