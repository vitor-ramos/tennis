package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.getFormattedPoints

class HistoryAdapter(private val inflater: LayoutInflater, private val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    var content = arrayOf<MatchEntity?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(inflater.inflate(R.layout.component_match, parent, false))
    }

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        content[position]?.let {
            with(holder) {
                component_match_layout.setOnClickListener {
                    onItemClick(holder.adapterPosition)
                }

                component_match_started.text = it.started.toString()

                component_match_host_name.text = it.hostName
                component_match_host_points.text = getFormattedPoints(it.hostPoints)
                component_match_host_games.text = it.hostGames.toString()
                component_match_host_sets.text = it.hostSets.toString()
                component_match_guest_name.text = it.guestName
                component_match_guest_points.text = getFormattedPoints(it.guestPoints)
                component_match_guest_games.text = it.guestGames.toString()
                component_match_guest_sets.text = it.guestSets.toString()
            }
        }
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val component_match_layout: ConstraintLayout = itemView.findViewById(R.id.component_match_layout)
        val component_match_started: AppCompatTextView = itemView.findViewById(R.id.component_match_started)
        val component_match_host_name: AppCompatTextView = itemView.findViewById(R.id.component_match_host_name)
        val component_match_host_points: AppCompatTextView = itemView.findViewById(R.id.component_match_host_points)
        val component_match_host_games: AppCompatTextView = itemView.findViewById(R.id.component_match_host_games)
        val component_match_host_sets: AppCompatTextView = itemView.findViewById(R.id.component_match_host_sets)
        val component_match_guest_name: AppCompatTextView = itemView.findViewById(R.id.component_match_guest_name)
        val component_match_guest_points: AppCompatTextView = itemView.findViewById(R.id.component_match_guest_points)
        val component_match_guest_games: AppCompatTextView = itemView.findViewById(R.id.component_match_guest_games)
        val component_match_guest_sets: AppCompatTextView = itemView.findViewById(R.id.component_match_guest_sets)
    }
}
