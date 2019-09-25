package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.viewModel.MainViewModel

class MainAdapter(activity: AppCompatActivity, initialState: HistoryState) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val viewModel = ViewModelProviders.of(activity)[MainViewModel::class.java]

    init {
        viewModel.state().observe(activity, Observer {
            state = it
        })
    }

    private var state: HistoryState = initialState
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
        return when (state) {
            HistoryState.NO_MATCH, HistoryState.STARTING -> content.size + 2
            HistoryState.CURRENT -> content.size + 3
        }
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
            ItemViewType.TITLE_CURRENT -> with(holder as DividerViewHolder) {
                tvDividerTitle.text = holder.itemView.context.getString(R.string.current_match)
            }
            ItemViewType.CURRENT -> {
                // TODO: prepare current match
            }
            ItemViewType.START -> with(holder as StartViewHolder) {
                llItemStartAdd.setOnClickListener {
                    viewModel.onClickStart()
                }
            }
            ItemViewType.STARTING -> with(holder as StartingViewHolder) {
                ibHistoryStartingClose.setOnClickListener {
                    viewModel.onClickCancelStart()
                }
            }
            ItemViewType.TITLE_HISTORY -> with(holder as DividerViewHolder) {
                tvDividerTitle.text = holder.itemView.context.getString(R.string.match_history)

            }
            ItemViewType.MATCH -> content[getIndex(position)]?.let {
                (holder as MatchViewHolder).bind(it, position == itemCount - 1)
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
