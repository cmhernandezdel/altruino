package com.cmhernandezdel.altruino.databinding

import android.view.View
import androidx.databinding.BindingAdapter

object BooleanToVisibilityAdapter {
    @BindingAdapter
    @JvmStatic
    fun setVisibility(view: View, value: Boolean) {
        view.visibility = if (value) View.VISIBLE else View.GONE
    }
}