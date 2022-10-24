package com.example.pet_growth_journal.ui.dailygrow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pet_growth_journal.databinding.ItemDailyGrowBinding

class DailyGrowAdapter: ListAdapter<DailyGrowModel, DailyGrowAdapter.DailyGrowViewHolder>(DailyGrowDiffCallback()) {

    class DailyGrowViewHolder(val binding: ItemDailyGrowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: DailyGrowModel) {
            binding.model = model
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyGrowViewHolder {
        return DailyGrowViewHolder(
            ItemDailyGrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DailyGrowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

private class DailyGrowDiffCallback: DiffUtil.ItemCallback<DailyGrowModel>() {
    override fun areContentsTheSame(oldItem: DailyGrowModel, newItem: DailyGrowModel): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: DailyGrowModel, newItem: DailyGrowModel): Boolean {
        return oldItem.id == newItem.id
    }
}