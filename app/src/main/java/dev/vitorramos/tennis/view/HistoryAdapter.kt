package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.getFormattedDate

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
                cdLayout.setOnClickListener {
                    onItemClick(holder.adapterPosition)
                }
                tvStarted.text = getFormattedDate(holder.itemView.resources, it.started)
                tvHostName.text = it.hostName
                tvHostSets.text = it.hostSets.toString()
                tvGuestName.text = it.guestName
                tvGuestSets.text = it.guestSets.toString()
            }
        }
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cdLayout: MaterialCardView = itemView.findViewById(R.id.cd_item_history_layout)
        val tvStarted: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_started)
        val tvHostName: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_host_name)
        val tvHostSets: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_host_sets)
        val tvGuestName: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_guest_name)
        val tvGuestSets: AppCompatTextView = itemView.findViewById(R.id.tv_item_history_guest_sets)
    }
}
