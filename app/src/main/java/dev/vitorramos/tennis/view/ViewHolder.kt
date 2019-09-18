package dev.vitorramos.tennis.view

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import dev.vitorramos.tennis.R

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cdLayout: MaterialCardView = itemView.findViewById(R.id.cd_item_history_layout)
    val tvStarted: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_started)
    val tvHostName: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_host_name)
    val tvHostSets: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_host_sets)
    val tvGuestName: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_guest_name)
    val tvGuestSets: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_guest_sets)
}
