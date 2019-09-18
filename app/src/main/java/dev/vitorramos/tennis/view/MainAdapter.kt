package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.getFormattedDate

class MainAdapter(
    private val state: HistoryState,
    private val inflater: LayoutInflater,
    private val onItemClick: (Int?) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var content = arrayOf<MatchEntity?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(ItemViewType.values()[viewType]) {
            ItemViewType.TITLE_START -> DividerViewHolder(
                inflater.inflate(
                    R.layout.item_divider,
                    parent,
                    true
                ),
                parent.context.getString(R.string.on_going_match)
            )
            ItemViewType.ON_GOING -> {
            }
            ItemViewType.START_MATCH -> {
            }
            ItemViewType.STARTING -> StartingViewHolder(
                inflater.inflate(R.layout.item_starting, parent, true)
            )
            ItemViewType.TITLE_HISTORY -> DividerViewHolder(
                inflater.inflate(
                    R.layout.item_divider,
                    parent,
                    true
                ),
                parent.context.getString(R.string.match_history)
            )
            ItemViewType.MATCH -> {
            }
        }
    }

    fun _onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) StartMatchViewHolder(
            inflater.inflate(
                R.layout.item_start,
                parent,
                false
            )
        )
        else ViewHolder(
            inflater.inflate(
                R.layout.item_match,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return content.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (state) {
            HistoryState.NO_MATCH -> {
                when (position) {
                    0 -> ItemViewType.START_MATCH
                    1 -> ItemViewType.TITLE_HISTORY
                    else -> ItemViewType.MATCH
                }.ordinal
            }
            HistoryState.STARTING -> {
                when (position) {
                    0 -> ItemViewType.STARTING
                    1 -> ItemViewType.TITLE_HISTORY
                    else -> ItemViewType.MATCH
                }.ordinal
            }
            HistoryState.ON_GOING -> {
                when (position) {
                    0 -> ItemViewType.TITLE_START
                    1 -> ItemViewType.ON_GOING
                    2 -> ItemViewType.TITLE_HISTORY
                    else -> ItemViewType.MATCH
                }.ordinal
            }
        }
    }

    private fun getIndex(position: Int): Int {
        return when (state) {
            HistoryState.NO_MATCH, HistoryState.STARTING -> position - 2
            HistoryState.ON_GOING -> position - 3
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(ItemViewType.values()[getItemViewType(position)]) {
            ItemViewType.TITLE_START -> {
            }
            ItemViewType.ON_GOING -> {
            }
            ItemViewType.START_MATCH -> {
            }
            ItemViewType.STARTING -> {
            }
            ItemViewType.TITLE_HISTORY -> {
            }
            ItemViewType.MATCH -> {
            }
        }
    }

    fun _onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0 && holder::class == StartMatchViewHolder::class) {
            with(holder as StartMatchViewHolder) {
                llItemHistoryAdd.setOnClickListener {
                    onItemClick(null)
                }
            }
        } else if (holder::class == ViewHolder::class) {
            content[position - 1]?.let {
                with(holder as ViewHolder) {
                    cdLayout.setOnClickListener {
                        onItemClick(holder.adapterPosition - 1)
                    }
                    tvStarted.text = getFormattedDate(
                        holder.itemView.resources,
                        it.started
                    )
                    tvHostName.text = it.hostName
                    tvHostSets.text = it.hostSets.toString()
                    tvGuestName.text = it.guestName
                    tvGuestSets.text = it.guestSets.toString()

                    if (position == itemCount - 1) {
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

    enum class HistoryState {
        NO_MATCH,
        STARTING,
        ON_GOING
    }

    private enum class ItemViewType {
        TITLE_START,
        ON_GOING,
        START_MATCH,
        STARTING,
        TITLE_HISTORY,
        MATCH,
    }
}
