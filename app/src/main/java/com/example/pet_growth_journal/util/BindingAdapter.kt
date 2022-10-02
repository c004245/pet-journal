package com.example.pet_growth_journal.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:glideUrl")
fun setImageUrl(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).into(view)
}
