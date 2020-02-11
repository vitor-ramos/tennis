package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.vitorramos.tennis.Match
import dev.vitorramos.tennis.databinding.ItemMatchBinding
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.viewModel.MainViewModel

class MainAdapter :
    ListAdapter<MatchEntity, MatchViewHolder>(object : DiffUtil.ItemCallback<MatchEntity>() {
        override fun areItemsTheSame(oldItem: MatchEntity, newItem: MatchEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MatchEntity, newItem: MatchEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode() && oldItem == newItem
        }
    }) {

    var vm: MainViewModel? = null

    var content: Array<MatchEntity?> = arrayOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMatchBinding.inflate(layoutInflater, parent, false)
        return MatchViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        getItem(holder.adapterPosition)?.let {
            holder.bind(
                holder.itemView.resources,
                it,
                holder.adapterPosition == itemCount - 1,
                { vm?.addPoint(holder.adapterPosition, Match.WhichPlayer.HOST) },
                { vm?.addPoint(holder.adapterPosition, Match.WhichPlayer.GUEST) },
                { vm?.deleteMatch(holder.adapterPosition) }
            )
        }
    }
}
