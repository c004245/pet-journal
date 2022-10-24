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
class TotalViewModel @Inject constructor() : ViewModel(), OnClickTotalCategoryListener, OnClickTotalSummaryListener {
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

    private val _totalSummarys = MutableLiveData<List<TotalSummaryModel>>()
    val totalSummarys: LiveData<List<RecyclerItem>>
        get() = Transformations.map(_totalSummarys) { model ->
            model.map { summaryModel ->
                TotalSummaryModel(
                    summaryModel.id,
                    summaryModel.category,
                    summaryModel.title,
                    summaryModel.date,
                    summaryModel.thumbnail
                ).toRecyclerItem(this)
            }
        }

    init {
        getTotalCategory()
        getTotalSummary()
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

    fun getTotalSummary() {
        _totalSummarys.value = listOf(
            TotalSummaryModel(
                id = 0,
                category = Category.BATH,
                title = "목욕",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
            TotalSummaryModel(
                id = 1,
                category = Category.BATH,
                title = "밥",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
            TotalSummaryModel(
                id = 2,
                category = Category.BATH,
                title = "병원",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
            TotalSummaryModel(
                id = 3,
                category = Category.BATH,
                title = "목욕",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
            TotalSummaryModel(
                id = 4,
                category = Category.BATH,
                title = "밥",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
            TotalSummaryModel(
                id = 5,
                category = Category.BATH,
                title = "병원",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
            TotalSummaryModel(
                id = 6,
                category = Category.BATH,
                title = "목욕",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
            TotalSummaryModel(
                id = 7,
                category = Category.BATH,
                title = "밥",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
            TotalSummaryModel(
                id = 8,
                category = Category.BATH,
                title = "병원",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
            TotalSummaryModel(
                id = 9,
                category = Category.BATH,
                title = "목욕",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
            TotalSummaryModel(
                id = 10,
                category = Category.BATH,
                title = "밥",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
            TotalSummaryModel(
                id = 11,
                category = Category.BATH,
                title = "병원",
                date = "2022.06.01(일) 14:56",
                thumbnail = ""
            ),
        )
    }
    override fun onClickTotalCategory(id: Int) {
        Log.d("HWO", "onClickTotalCategory -> $id")

        _totalCategorys.value = _totalCategorys.value?.map {
            if (it.id == id) {
                TotalCategoryModel(id, category = it.category, isSelected = !it.isSelected)
            } else {
                TotalCategoryModel(
                    it.id, it.category, it.isSelected
                )
            }
        }
    }

    override fun onClickTotalSummary(id: Int) {
        Log.d("HWO", "onClickTotalSummary -> $id")
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
    val dailySummaryModel: List<TotalSummaryModel>? = null
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

/** 하얀색 카드 */
data class TotalSummaryModel(
    val id: Int,
    val category: Category,
    val title: String,
    val date: String,
    val thumbnail: String? = null
): RecyclerItemComparator {
    override fun isSameContent(other: Any): Boolean {
        return this == (other as TotalSummaryModel)
    }

    override fun isSameItem(other: Any): Boolean {
        return false
    }
}

fun TotalSummaryModel.toRecyclerItem(onClickTotalSummaryListener: OnClickTotalSummaryListener) = RecyclerItem(
    BR.model to this,
    listOf(
        BR.actionListener to onClickTotalSummaryListener
    ),
    R.layout.item_total_summary
)


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

interface OnClickTotalSummaryListener {
    fun onClickTotalSummary(id: Int)
}

data class CategoryModel(
    val id: Int,
    val category: Category,
    val count: Int
)