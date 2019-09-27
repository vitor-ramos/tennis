package dev.vitorramos.tennis.binding

import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter

@BindingAdapter("android:layout_marginBottom")
fun setMarginBottom(v: View, marginBottom: Float) {
    v.layoutParams = (v.layoutParams as ViewGroup.MarginLayoutParams).apply {
        setMargins(
            leftMargin,
            topMargin,
            rightMargin,
            marginBottom.toInt()
        )
    }
}
