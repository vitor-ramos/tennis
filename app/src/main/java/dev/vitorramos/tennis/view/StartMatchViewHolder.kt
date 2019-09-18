package dev.vitorramos.tennis.view

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R

class StartMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val llItemHistoryAdd: LinearLayout = itemView.findViewById(R.id.ll_item_history_add)
}
