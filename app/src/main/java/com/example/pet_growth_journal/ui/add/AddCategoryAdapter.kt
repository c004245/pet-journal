package com.example.pet_growth_journal.ui.add

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.setMargins
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.pet_growth_journal.R
import com.example.pet_growth_journal.databinding.ItemAddCategoryBinding

class AddCategoryAdapter(
    private val context: Context,
    val onClickCategory: (category: AddCategoryModel) -> Unit
): ListAdapter<AddCategoryModel, AddCategoryAdapter.AddCategoryViewHolder>(
    ItemDiffCallback()
) {

    class AddCategoryViewHolder(val binding: ItemAddCategoryBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddCategoryViewHolder {

        val binding: ItemAddCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.item_add_category, parent, false
        )

        val layoutParams = binding.root.layoutParams as GridLayoutManager.LayoutParams

        val displayMetrics = DisplayMetrics()

        val windowManager = parent.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val px = 4 * displayMetrics.densityDpi / 160
        layoutParams.setMargins(px)
        binding.root.layoutParams = layoutParams


        return AddCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddCategoryViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.run {
            if (item.isSelected) {
                binding.clCategory.background =
                    AppCompatResources.getDrawable(context, R.drawable.background_radius_16_add_category_select)

                binding.tvCategory.setTextColor(context.resources.getColor(R.color.white))
                binding.ivAddCategory.setImageResource(item.selectIcon)
            } else {
                binding.clCategory.background =
                    AppCompatResources.getDrawable(context, R.drawable.background_radius_16_add_category_unselect)
                binding.tvCategory.setTextColor(context.resources.getColor(R.color.black_21))
                binding.ivAddCategory.setImageResource(item.icon)
            }

            binding.tvCategory.text = item.category
            binding.root.setOnClickListener {
                Log.d("HWO", "onClick")
                onClickCategory(item)
            }
        }

    }
    class ItemDiffCallback: DiffUtil.ItemCallback<AddCategoryModel>() {
        override fun areContentsTheSame(
            oldItem: AddCategoryModel,
            newItem: AddCategoryModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: AddCategoryModel,
            newItem: AddCategoryModel
        ): Boolean {
            return (oldItem.id == newItem.id) && (oldItem.isSelected && newItem.isSelected)
        }


    }
}


data class AddCategoryModel(
    val id: Int,
    val category: String,
    val icon: Int,
    val selectIcon : Int,
    val isSelected: Boolean = false
)