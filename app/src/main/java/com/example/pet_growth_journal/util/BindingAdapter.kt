package com.example.pet_growth_journal.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pet_growth_journal.R

@BindingAdapter("app:glideUrl")
fun setImageUrl(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("app:fontStyle")
fun setFontStyle(view: TextView, isSelected: Boolean) {
    if (isSelected) {
        view.setTextAppearance(R.style.Text_NotoSans_Bold)
    } else {
        view.setTextAppearance(R.style.Text_NotoSans_Medium)
    }
}

@BindingAdapter("app:categoryBackground")
fun setCategoryBackground(view: ImageView, icon: Int) {
    view.setImageResource(icon)
}