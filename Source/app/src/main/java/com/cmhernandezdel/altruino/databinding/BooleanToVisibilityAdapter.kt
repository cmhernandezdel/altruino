package com.cmhernandezdel.altruino.databinding

import android.view.View
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter

object BooleanToVisibilityAdapter {
    @BindingAdapter("android:visibility")
    @JvmStatic
    fun setVisibility(view: View, value: Boolean) {
        view.visibility = if (value) View.VISIBLE else View.GONE
    }
}