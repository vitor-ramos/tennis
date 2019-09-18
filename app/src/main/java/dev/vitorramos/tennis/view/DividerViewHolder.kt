package dev.vitorramos.tennis.view

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R

class DividerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvDividerTitle: AppCompatTextView = itemView.findViewById(R.id.tv_divider_title)
}
