package com.example.pet_growth_journal.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pet_growth_journal.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(): ViewModel(){

    private var _currentType = MutableLiveData(CurrentType.PICTURE)
    val currentType: LiveData<CurrentType>
        get() = _currentType

    private var _pictureType = MutableLiveData<PictureType>()
    val pictureType: LiveData<PictureType>
        get() = _pictureType

    private val _addCategorys = MutableLiveData<List<AddCategoryModel>>()
    val addCategorys: LiveData<List<AddCategoryModel>>
        get() = _addCategorys


    private val _addEmotions = MutableLiveData<List<AddEmotionModel>>()
    val addEmotions: LiveData<List<AddEmotionModel>>
        get() = _addEmotions

    init {
    }

    private fun addDummyEmotion() {
        _addEmotions.value = listOf(
            AddEmotionModel(
                id = 0,
                icon = R.drawable.ic_smile_emotion_unselect,
                selectIcon = R.drawable.ic_smile_emotion_select
            ),
            AddEmotionModel(
                id = 1,
                icon = R.drawable.ic_none_emotion_unselect,
                selectIcon = R.drawable.ic_none_emotion_select
            ),

            AddEmotionModel(
                id = 2,
                icon = R.drawable.ic_hurt_emotion_unselect,
                selectIcon = R.drawable.ic_hurt_emotion_select
            ),

            AddEmotionModel(
                id = 3,
                icon = R.drawable.ic_happy_emotion_unselect,
                selectIcon = R.drawable.ic_happy_emotion_select
            ),

            AddEmotionModel(
                id = 4,
                icon = R.drawable.ic_kiss_emotion_unselect,
                selectIcon = R.drawable.ic_kiss_emotion_select
            ),

            AddEmotionModel(
                id = 5,
                icon = R.drawable.ic_happy2_emotion_unselect,
                selectIcon = R.drawable.ic_happy2_emotion_select
            ),

            AddEmotionModel(
                id = 6,
                icon = R.drawable.ic_sad_emotion_unselect,
                selectIcon = R.drawable.ic_sad_emotion_select
            ),

            AddEmotionModel(
                id = 7,
                icon = R.drawable.ic_angry_emotion_unselect,
                selectIcon = R.drawable.ic_angry_emotion_select
            ),
        )

    }
    private fun addDummyCategory() {
        _addCategorys.value = listOf(
            AddCategoryModel(
                id = 0,
                category = "간식",
                icon = R.drawable.ic_snack_black,
                selectIcon = R.drawable.ic_snack,
                isSelected = false
            ),
            AddCategoryModel(
                id = 1,
                category = "물 마시기",
                icon = R.drawable.ic_water_black,
                selectIcon = R.drawable.ic_water,
                isSelected = false
            ),
            AddCategoryModel(
                id = 2,
                category = "약 먹기",
                icon = R.drawable.ic_medicine_black,
                selectIcon = R.drawable.ic_medicine_white,
                isSelected = false
            ),
            AddCategoryModel(
                id = 3,
                category = "목욕",
                icon = R.drawable.ic_bath_black,
                selectIcon = R.drawable.ic_bath_white,
                isSelected = false
            ),AddCategoryModel(
                id = 4,
                category = "병원",
                icon = R.drawable.ic_hospital_black,
                selectIcon = R.drawable.ic_hospital_white,
                isSelected = false
            ),AddCategoryModel(
                id = 5,
                category = "산책",
                icon = R.drawable.ic_walk_black,
                selectIcon = R.drawable.ic_walk_white,
                isSelected = false
            ),AddCategoryModel(
                id = 6,
                category = "수면",
                icon = R.drawable.ic_sleep_black,
                selectIcon = R.drawable.ic_sleep_white,
                isSelected = false
            ),
            AddCategoryModel(
                id = 7,
                category = "실내놀이",
                icon = R.drawable.ic_inside_black,
                selectIcon = R.drawable.ic_inside_white,
                isSelected = false
            ),
            AddCategoryModel(
                id = 8,
                category = "실외놀이",
                icon = R.drawable.ic_outside_black,
                selectIcon = R.drawable.ic_outside_white,
                isSelected = false
            ),
            AddCategoryModel(
                id = 9,
                category = "이벤트",
                icon = R.drawable.ic_event_black,
                selectIcon = R.drawable.ic_event_white,
                isSelected = false
            ),
            AddCategoryModel(
                id = 10,
                category = "기타",
                icon = R.drawable.ic_plus_black,
                selectIcon = R.drawable.ic_plus_white,
                isSelected = false
            ),
        )
    }

    fun setCurrentType(type: CurrentType) {
        if (type == CurrentType.CATEGORY) {
            addDummyCategory()
        } else if (type == CurrentType.EMOTION) {
            addDummyEmotion()
        }
        _currentType.value = type
    }

    fun onClickCategory(id: Int) {
        val data = addCategorys.value?.map {
            if (it.id == id) {
                AddCategoryModel(
                    id = it.id,
                    category = it.category,
                    icon = it.icon,
                    selectIcon = it.selectIcon,
                    isSelected = true
                )
            } else {
                AddCategoryModel(
                    id = it.id,
                    category = it.category,
                    icon = it.icon,
                    selectIcon = it.selectIcon,
                    isSelected = false
                )
            }
        }
       _addCategorys.value = data
    }

    fun onClickEmotion(id: Int) {
        val data = addEmotions.value?.map {
            if (it.id == id) {
                AddEmotionModel(
                    id = it.id,
                    icon = it.icon,
                    selectIcon = it.selectIcon,
                    isSelected = true
                )
            } else {
                AddEmotionModel(
                    id = it.id,
                    icon = it.icon,
                    selectIcon = it.selectIcon,
                    isSelected = false
                )
            }
        }
        _addEmotions.value = data

    }
}


enum class CurrentType {
    PICTURE, CATEGORY, EMOTION
}

enum class PictureType {
    CAMERA, GALLERY
}