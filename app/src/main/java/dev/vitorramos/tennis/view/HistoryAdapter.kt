package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity

class HistoryAdapter(private val inflater: LayoutInflater, private val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    var content = arrayOf<MatchEntity?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(inflater.inflate(R.layout.item_history, parent, false))
    }

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        content[position]?.let {
            with(holder) {
                item_history_layout.setOnClickListener {
                    onItemClick(holder.adapterPosition)
                }

                history_current_host_name.text = it.hostName
                history_current_host_points.text = it.hostPoints.toString()
                history_current_host_games.text = it.hostGames.toString()
                history_current_host_sets.text = it.hostSets.toString()
                history_current_guest_name.text = it.guestName
                history_current_guest_points.text = it.guestPoints.toString()
                history_current_guest_games.text = it.guestGames.toString()
                history_current_guest_sets.text = it.guestSets.toString()
            }
        }
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_history_layout: ConstraintLayout = itemView.findViewById(R.id.item_history_layout)
        val history_current_host_name: AppCompatTextView = itemView.findViewById(R.id.history_current_host_name)
        val history_current_host_points: AppCompatTextView = itemView.findViewById(R.id.history_current_host_points)
        val history_current_host_games: AppCompatTextView = itemView.findViewById(R.id.history_current_host_games)
        val history_current_host_sets: AppCompatTextView = itemView.findViewById(R.id.history_current_host_sets)
        val history_current_guest_name: AppCompatTextView = itemView.findViewById(R.id.history_current_guest_name)
        val history_current_guest_points: AppCompatTextView = itemView.findViewById(R.id.history_current_guest_points)
        val history_current_guest_games: AppCompatTextView = itemView.findViewById(R.id.history_current_guest_games)
        val history_current_guest_sets: AppCompatTextView = itemView.findViewById(R.id.history_current_guest_sets)
    }
}
