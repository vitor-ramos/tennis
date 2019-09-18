package dev.vitorramos.tennis.view

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R

class HistoryDividerViewHolder(itemView: View, text: String) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.findViewById<AppCompatTextView>(R.id.tv_start_title).text = text
    }
}
