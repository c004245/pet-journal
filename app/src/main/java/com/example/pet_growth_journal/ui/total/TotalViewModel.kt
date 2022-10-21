package com.example.pet_growth_journal.ui.total

import android.util.Log
import androidx.lifecycle.*
import com.example.pet_growth_journal.BR
import com.example.pet_growth_journal.R
import com.example.pet_growth_journal.ui.dailygrow.Category
import com.example.pet_growth_journal.util.RecyclerItem
import com.example.pet_growth_journal.util.RecyclerItemComparator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TotalViewModel @Inject constructor() : ViewModel(), OnClickTotalCategoryListener {
    private val _totalCategorys = MutableLiveData<List<TotalCategoryModel>>()
    val totalCategorys: LiveData<List<RecyclerItem>>
        get() = Transformations.map(_totalCategorys) { model ->
            model.map { categoryModel ->
                TotalCategoryModel(
                    categoryModel.id,
                    categoryModel.category,
                    categoryModel.isSelected
                ).toRecyclerItem(this)
            }
        }

    init {
        getTotalCategory()
    }

    fun getTotalCategory() {
        _totalCategorys.value = listOf(
            TotalCategoryModel(
                id = 0,
                category = "전체",
                isSelected = true
            ),
            TotalCategoryModel(
                id = 1,
                category = "간식",
                isSelected = false
            )
        )
    }
    override fun onClickTotalCategory(id: Int) {
        Log.d("HWO", "onClickTotalCategory -> $id")

        _totalCategorys.value = _totalCategorys.value.find {
            it.isSelected == id
        }
        }
    }

}

data class CommonTotalModel(
    val monthSummaryModel: List<MonthSummaryModel>? = null,
)

/** 보라색 카드 */
data class MonthSummaryModel(
    val id: Int,
    val month: Int,
    val name: String,
    val topCategory: CategoryModel,
    val dailySummaryModel: List<DailySummaryModel>? = null
)

data class TotalCategoryModel(
    val id: Int,
    val category: String,
    val isSelected: Boolean = false
): RecyclerItemComparator {
    override fun isSameContent(other: Any): Boolean {
        return this == (other as TotalCategoryModel)
    }

    override fun isSameItem(other: Any): Boolean {
        return false
    }
}

fun TotalCategoryModel.toRecyclerItem(onClickTotalCategoryListener: OnClickTotalCategoryListener) = RecyclerItem(
    BR.model to this,
    listOf(
        BR.actionListener to onClickTotalCategoryListener
    ),
    R.layout.item_total_category
)

interface OnClickTotalCategoryListener {
    fun onClickTotalCategory(id: Int)
}
/** 하얀색 카드 */
data class DailySummaryModel(
    val id: Int,
    val category: Category,
    val title: String,
    val time: String
)


data class CategoryModel(
    val id: Int,
    val category: Category,
    val count: Int
)