package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.getFormattedDate

class HistoryAdapter(
    private val inflater: LayoutInflater,
    private val onFirstItemClick: () -> Unit,
    private val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var content = arrayOf<MatchEntity?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) HistoryAddViewHolder(
            inflater.inflate(
                R.layout.item_history_add,
                parent,
                false
            )
        )
        else HistoryViewHolder(
            inflater.inflate(
                R.layout.item_history,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return content.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0 && holder::class == HistoryAddViewHolder::class) {
            with(holder as HistoryAddViewHolder) {
                llItemHistoryAdd.setOnClickListener {
                    onFirstItemClick()
                }
            }
        } else if (holder::class == HistoryViewHolder::class) {
            content[position - 1]?.let {
                with(holder as HistoryViewHolder) {
                    cdLayout.setOnClickListener {
                        onItemClick(holder.adapterPosition - 1)
                    }
                    tvStarted.text = getFormattedDate(holder.itemView.resources, it.started)
                    tvHostName.text = it.hostName
                    tvHostSets.text = it.hostSets.toString()
                    tvGuestName.text = it.guestName
                    tvGuestSets.text = it.guestSets.toString()

                    if(position == itemCount - 1) {
                        cdLayout.layoutParams =
                            (cdLayout.layoutParams as MarginLayoutParams).apply {
                                val defaultMargin = holder.itemView.context.resources
                                    .getDimension(R.dimen.default_margin)
                                setMargins(
                                    leftMargin,
                                    topMargin,
                                    rightMargin,
                                    defaultMargin.toInt()
                                )
                            }
                    }
                }
            }
        }
    }

    class HistoryAddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val llItemHistoryAdd: LinearLayout = itemView.findViewById(R.id.ll_item_history_add)
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
