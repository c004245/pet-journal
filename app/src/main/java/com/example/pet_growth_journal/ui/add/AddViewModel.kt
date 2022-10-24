package com.example.pet_growth_journal.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(): ViewModel() {

    private var _currentType = MutableLiveData<CurrentType>(CurrentType.PICTURE)
    val currentType: LiveData<CurrentType>
        get() = _currentType

    private var _pictureType = MutableLiveData<PictureType>()
    val pictureType: LiveData<PictureType>
        get() = _pictureType

    fun onClickCamera() {
        _pictureType.value = PictureType.CAMERA
    }

    fun onClickGallery() {
        _pictureType.value = PictureType.GALLERY

    }
}

enum class CurrentType {
    PICTURE, CATEGORY, EMOTION
}

enum class PictureType {
    CAMERA, GALLERY
}