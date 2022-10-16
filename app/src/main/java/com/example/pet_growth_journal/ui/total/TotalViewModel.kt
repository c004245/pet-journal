package com.example.pet_growth_journal.ui.total

import androidx.lifecycle.ViewModel
import com.example.pet_growth_journal.ui.dailygrow.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TotalViewModel @Inject constructor() : ViewModel() {

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