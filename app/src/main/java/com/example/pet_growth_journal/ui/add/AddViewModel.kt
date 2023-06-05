package com.example.pet_growth_journal.ui.add

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.pet_growth_journal.BR
import com.example.pet_growth_journal.R
import com.example.pet_growth_journal.util.RecyclerItem
import com.example.pet_growth_journal.util.RecyclerItemComparator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(): ViewModel(), OnClickAddCategoryListener {

    private var _currentType = MutableLiveData<CurrentType>(CurrentType.PICTURE)
    val currentType: LiveData<CurrentType>
        get() = _currentType

    private var _pictureType = MutableLiveData<PictureType>()
    val pictureType: LiveData<PictureType>
        get() = _pictureType

    private val _addCategorys = MutableLiveData<List<AddCategoryModel>>()
    val addCategorys: LiveData<List<AddCategoryModel>>
        get() = _addCategorys

    init {
        addDummyCategory()
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
                id = 0,
                category = "약 먹기",
                icon = R.drawable.ic_medicine_black,
                selectIcon = R.drawable.ic_medicine_white,
                isSelected = false
            ),
            AddCategoryModel(
                id = 0,
                category = "목욕",
                icon = R.drawable.ic_bath_black,
                selectIcon = R.drawable.ic_bath_white,
                isSelected = false
            ),AddCategoryModel(
                id = 0,
                category = "병원",
                icon = R.drawable.ic_hospital_black,
                selectIcon = R.drawable.ic_hospital_white,
                isSelected = false
            ),AddCategoryModel(
                id = 0,
                category = "산책",
                icon = R.drawable.ic_walk_black,
                selectIcon = R.drawable.ic_walk_white,
                isSelected = false
            ),AddCategoryModel(
                id = 0,
                category = "수면",
                icon = R.drawable.ic_sleep_black,
                selectIcon = R.drawable.ic_sleep_white,
                isSelected = false
            ),
            AddCategoryModel(
                id = 0,
                category = "실내놀이",
                icon = R.drawable.ic_inside_black,
                selectIcon = R.drawable.ic_inside_white,
                isSelected = false
            ),
            AddCategoryModel(
                id = 0,
                category = "실외놀이",
                icon = R.drawable.ic_outside_black,
                selectIcon = R.drawable.ic_outside_white,
                isSelected = false
            ),
            AddCategoryModel(
                id = 0,
                category = "이벤트",
                icon = R.drawable.ic_event_black,
                selectIcon = R.drawable.ic_event_white,
                isSelected = false
            ),
            AddCategoryModel(
                id = 0,
                category = "기타",
                icon = R.drawable.ic_plus_black,
                selectIcon = R.drawable.ic_plus_white,
                isSelected = false
            ),


        )
    }
    fun onClickCamera() {
        _pictureType.value = PictureType.CAMERA
    }

    fun onClickGallery() {
        _pictureType.value = PictureType.GALLERY
    }

    fun setCurrentType(type: CurrentType) {
        _currentType.value = type
    }

    override fun onClickAddCategory(id: Int) {

    }
}



interface OnClickAddCategoryListener {
    fun onClickAddCategory(id: Int)
}
enum class CurrentType {
    PICTURE, CATEGORY, EMOTION
}

enum class PictureType {
    CAMERA, GALLERY
}