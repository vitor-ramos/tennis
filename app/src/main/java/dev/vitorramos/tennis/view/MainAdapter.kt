package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.getFormattedDate

class MainAdapter(initialState: HistoryState) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var state: HistoryState = initialState

    var content = arrayOf<MatchEntity?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (ItemViewType.values()[viewType]) {
            ItemViewType.TITLE_CURRENT -> DividerViewHolder(
                inflater.inflate(
                    R.layout.item_divider,
                    parent,
                    false
                )
            )
            ItemViewType.CURRENT -> CurrentViewHolder(
                inflater.inflate(
                    R.layout.item_current,
                    parent,
                    false
                )
            )
            ItemViewType.START -> StartViewHolder(
                inflater.inflate(
                    R.layout.item_start,
                    parent,
                    false
                )
            )
            ItemViewType.STARTING -> StartingViewHolder(
                inflater.inflate(
                    R.layout.item_starting,
                    parent,
                    false
                )
            )
            ItemViewType.TITLE_HISTORY -> DividerViewHolder(
                inflater.inflate(
                    R.layout.item_divider,
                    parent,
                    false
                )
            )
            ItemViewType.MATCH -> MatchViewHolder(
                inflater.inflate(
                    R.layout.item_match,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        // TODO: fix bug
        return content.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (state) {
            HistoryState.NO_MATCH -> {
                when (position) {
                    0 -> ItemViewType.START
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
            HistoryState.CURRENT -> {
                when (position) {
                    0 -> ItemViewType.TITLE_CURRENT
                    1 -> ItemViewType.CURRENT
                    2 -> ItemViewType.TITLE_HISTORY
                    else -> ItemViewType.MATCH
                }.ordinal
            }
        }
    }

    private fun getIndex(position: Int): Int {
        return when (state) {
            HistoryState.NO_MATCH, HistoryState.STARTING -> position - 2
            HistoryState.CURRENT -> position - 3
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (ItemViewType.values()[getItemViewType(position)]) {
            ItemViewType.TITLE_CURRENT -> {
                with(holder as DividerViewHolder) {
                    tvDividerTitle.text = holder.itemView.context.getString(R.string.current_match)
                }
            }
            ItemViewType.CURRENT -> {
                // TODO: prepare current match
            }
            ItemViewType.START -> {
                with(holder as StartViewHolder) {
                    llItemStartAdd.setOnClickListener {
                        // TODO: show form
                    }
                }
            }
            ItemViewType.STARTING -> {
                // TODO: prepare form
            }
            ItemViewType.TITLE_HISTORY -> {
                with(holder as DividerViewHolder) {
                    tvDividerTitle.text = holder.itemView.context.getString(R.string.match_history)
                }
            }
            ItemViewType.MATCH -> {
                content[getIndex(position)]?.let {
                    with(holder as MatchViewHolder) {
                        tvHostName.text = it.hostName
                        tvGuestName.text = it.guestName

                        tvHostPoints.text = it.hostPoints.toString()
                        tvGuestPoints.text = it.guestPoints.toString()

                        tvHostGames.text = it.hostGames.toString()
                        tvGuestGames.text = it.guestGames.toString()

                        tvHostSets.text = it.hostSets.toString()
                        tvGuestSets.text = it.guestSets.toString()

                        // TODO: proper duration time
                        duration = -1

                        tvStarted.text = getFormattedDate(
                            holder.itemView.resources,
                            it.started
                        )

                        if (position == itemCount - 1) {
                            cvItemMatch.layoutParams =
                                (cvItemMatch.layoutParams as MarginLayoutParams).apply {
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
    }

    enum class HistoryState {
        NO_MATCH,
        STARTING,
        CURRENT
    }

    private enum class ItemViewType {
        TITLE_CURRENT,
        CURRENT,
        START,
        STARTING,
        TITLE_HISTORY,
        MATCH,
    }
}
