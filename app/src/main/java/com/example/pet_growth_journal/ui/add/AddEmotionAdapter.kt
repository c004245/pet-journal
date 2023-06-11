package com.example.pet_growth_journal.ui.add

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ListAdapter
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.setMargins
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pet_growth_journal.R
import com.example.pet_growth_journal.databinding.ItemAddEmotionBinding

class AddEmotionAdapter(
    private val context: Context,
    val onClickEmotion: (emotion: AddEmotionModel) -> Unit
): androidx.recyclerview.widget.ListAdapter<AddEmotionModel, AddEmotionAdapter.AddEmotionViewHolder>(
    ItemDiffCallback()
) {


    class AddEmotionViewHolder(val binding: ItemAddEmotionBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddEmotionViewHolder {
        val binding: ItemAddEmotionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.item_add_emotion, parent, false)


        val layoutParams = binding.root.layoutParams as GridLayoutManager.LayoutParams

        val displayMetrics = DisplayMetrics()

        val windowManager = parent.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val px = 4 * displayMetrics.densityDpi / 160
        layoutParams.setMargins(px)
        binding.root.layoutParams = layoutParams

        return AddEmotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddEmotionViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.run {
            if (item.isSelected) {
                binding.clEmotion.background =
                    AppCompatResources.getDrawable(context, R.drawable.background_radius_16_add_category_select)

                binding.ivAddEmotion.setImageResource(item.selectIcon)
            } else {
                binding.clEmotion.background =
                    AppCompatResources.getDrawable(context, R.drawable.background_radius_16_add_category_unselect)

                binding.ivAddEmotion.setImageResource(item.icon)
            }

            binding.root.setOnClickListener {
                onClickEmotion(item)
            }
        }
    }

    class ItemDiffCallback: DiffUtil.ItemCallback<AddEmotionModel>() {
        override fun areContentsTheSame(
            oldItem: AddEmotionModel,
            newItem: AddEmotionModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: AddEmotionModel,
            newItem: AddEmotionModel
        ): Boolean {
            return (oldItem.id == newItem.id) && (oldItem.isSelected && newItem.isSelected)
        }


    }




}
data class AddEmotionModel(
    val id: Int,
    val icon: Int,
    val selectIcon: Int,
    val isSelected: Boolean = false
)