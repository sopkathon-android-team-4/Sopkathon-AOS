package com.sopt.sopkathon_aos.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("loadImageUrl")
    fun loadImage(view: ImageView, imageUrl: String?) {
        view.load(imageUrl)
    }
}
