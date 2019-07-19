package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity

class HistoryAdapter(private val inflater: LayoutInflater) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

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
                tvHistoryItemHostName.text = it.hostName
                tvHistoryItemGuestName.text = it.guestName
                tvHistoryItemHostSets.text = it.hostSets.toString()
                tvHistoryItemGuestSets.text = it.guestSets.toString()
            }
        }
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHistoryItemHostName: AppCompatTextView = itemView.findViewById(R.id.tv_history_item_host_name)
        val tvHistoryItemGuestName: AppCompatTextView = itemView.findViewById(R.id.tv_history_item_guest_name)
        val tvHistoryItemHostSets: AppCompatTextView = itemView.findViewById(R.id.tv_history_item_host_sets)
        val tvHistoryItemGuestSets: AppCompatTextView = itemView.findViewById(R.id.tv_history_item_guest_sets)
    }
}
