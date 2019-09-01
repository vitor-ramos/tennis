package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity

class HistoryAdapter(private val inflater: LayoutInflater, private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

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
                item_history_card.setOnClickListener {
                    onItemClick(holder.adapterPosition)
                }

                item_history_started.text = it.started.toString()

                item_history_host_name.text = it.hostName
                item_history_host_sets.text = it.hostSets.toString()
                item_history_guest_name.text = it.guestName
                item_history_guest_sets.text = it.guestSets.toString()
            }
        }
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_history_card: MaterialCardView =
            itemView.findViewById(R.id.item_history_card)
        val item_history_started: AppCompatTextView =
            itemView.findViewById(R.id.item_history_started)
        val item_history_host_name: AppCompatTextView =
            itemView.findViewById(R.id.item_history_host_name)
        val item_history_host_sets: AppCompatTextView =
            itemView.findViewById(R.id.item_history_host_sets)
        val item_history_guest_name: AppCompatTextView =
            itemView.findViewById(R.id.item_history_guest_name)
        val item_history_guest_sets: AppCompatTextView =
            itemView.findViewById(R.id.item_history_guest_sets)
    }
}
