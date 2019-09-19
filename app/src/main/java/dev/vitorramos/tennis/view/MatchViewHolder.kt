package dev.vitorramos.tennis.view

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import dev.vitorramos.tennis.R

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cvItemMatch: MaterialCardView = itemView.findViewById(R.id.cv_item_match)

    val tvHostName: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_host_name)
    val tvGuestName: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_guest_name)

    val tvHostPoints: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_host_points)
    val tvGuestPoints: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_guest_points)

    val tvHostGames: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_host_games)
    val tvGuestGames: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_guest_games)

    val tvHostSets: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_host_sets)
    val tvGuestSets: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_guest_sets)

    private val tvDuration: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_duration)
    var duration = -1
        set(value) {
            field = value
            val s = if (duration >= 0) "Tempo de jogo: $value" else "Tempo de jogo: -"
            tvDuration.text = s
        }

    val tvStarted: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_started)
}
