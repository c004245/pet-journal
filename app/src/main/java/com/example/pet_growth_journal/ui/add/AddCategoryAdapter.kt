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
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.pet_growth_journal.R
import com.example.pet_growth_journal.databinding.ItemAddCategoryBinding

class AddCategoryAdapter(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner
): ListAdapter<AddCategoryModel, AddCategoryCommonListViewHolder>(
    ItemDiffCallback()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddCategoryCommonListViewHolder {
        val binding: ItemAddCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_add_category,
            parent, false)


        binding.lifecycleOwner = lifecycleOwner
        val layoutParams = binding.root.layoutParams as GridLayoutManager.LayoutParams

        val displayMetrics = DisplayMetrics()

        val windowManager = parent.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val px = 4 * displayMetrics.densityDpi / 160
        layoutParams.setMargins(px)
        binding.root.layoutParams = layoutParams

        return AddCategoryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddCategoryCommonListViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item, context)
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


abstract class AddCategoryCommonListViewHolder(view: View) : ViewHolder(view) {
    abstract fun bind(item: AddCategoryModel, context: Context)
}

class AddCategoryListViewHolder(val binding: ItemAddCategoryBinding): AddCategoryCommonListViewHolder(binding.root) {
    override fun bind(item: AddCategoryModel, context: Context) {
        if (item.isSelected) {
            binding.clCategory.background =
                AppCompatResources.getDrawable(context, R.drawable.background_radius_16_add_category_select)

            binding.tvCategory.setTextColor(context.resources.getColor(R.color.white))
            binding.ivAddCategory.setImageResource(item.selectIcon)

//            binding.tvCategory.settextcolor
        } else {
            binding.clCategory.background =
                AppCompatResources.getDrawable(context, R.drawable.background_radius_16_add_category_unselect)
            binding.tvCategory.setTextColor(context.resources.getColor(R.color.black_21))
            binding.ivAddCategory.setImageResource(item.icon)
        }

        binding.tvCategory.text = item.category
    }
}


data class AddCategoryModel(
    val id: Int,
    val category: String,
    val icon: Int,
    val selectIcon : Int,
    val isSelected: Boolean = false
)