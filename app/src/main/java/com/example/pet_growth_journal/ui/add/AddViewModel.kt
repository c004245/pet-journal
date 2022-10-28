package com.example.pet_growth_journal.ui.add

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
    val addCategorys: LiveData<List<RecyclerItem>>
        get() = Transformations.map(_addCategorys) { model ->
            model.map { addCategoryModel ->
                AddCategoryModel(
                    addCategoryModel.id,
                    addCategoryModel.category,
                    addCategoryModel.isSelected
                ).toRecyclerItem(this)
            }
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

data class AddCategoryModel(
    val id: Int,
    val category: String,
    val isSelected: Boolean = false
): RecyclerItemComparator {
    override fun isSameContent(other: Any): Boolean {
        return this == (other as AddCategoryModel)
    }

    override fun isSameItem(other: Any): Boolean {
        return false
    }
}

fun AddCategoryModel.toRecyclerItem(onClickAddCategoryListener: OnClickAddCategoryListener) = RecyclerItem(
    BR.model to this,
    listOf(
        BR.actionListener to onClickAddCategoryListener
    ),
    R.layout.item_add_category
)

interface OnClickAddCategoryListener {
    fun onClickAddCategory(id: Int)
}
enum class CurrentType {
    PICTURE, CATEGORY, EMOTION
}

enum class PictureType {
    CAMERA, GALLERY
}