package dev.vitorramos.tennis.view

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R

class StartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val llItemStartAdd: LinearLayout = itemView.findViewById(R.id.ll_item_start_add)
}
