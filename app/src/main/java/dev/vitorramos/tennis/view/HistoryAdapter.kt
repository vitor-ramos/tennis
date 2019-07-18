package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity

class HistoryAdapter(
    private val inflater: LayoutInflater,
    var content: Array<MatchEntity?>
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(inflater.inflate(R.layout.item_history, parent, false))
    }

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        content[position]?.let {
            holder.tvHistoryItemHostName?.text = it.hostName
            holder.tvHistoryItemGuestName?.text = it.guestName
            holder.tvHistoryItemHostSets?.text = it.hostSets.toString()
            holder.tvHistoryItemGuestSets?.text = it.guestSets.toString()
        }
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvHistoryItemHostName: AppCompatTextView? = null
        var tvHistoryItemGuestName: AppCompatTextView? = null
        var tvHistoryItemHostSets: AppCompatTextView? = null
        var tvHistoryItemGuestSets: AppCompatTextView? = null
    }
}
