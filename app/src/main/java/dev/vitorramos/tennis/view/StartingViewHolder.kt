package dev.vitorramos.tennis.view

import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R

class StartingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ibHistoryStartingClose: AppCompatImageButton =
        itemView.findViewById(R.id.ib_history_starting_close)
}
