package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.Match
import dev.vitorramos.tennis.databinding.ItemMatchBinding
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.viewModel.MainViewModel

class MainAdapter(private val viewModel: MainViewModel?) : RecyclerView.Adapter<MatchViewHolder>() {
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

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        content[holder.adapterPosition]?.let {
            holder.bind(
                holder.itemView.resources,
                it,
                holder.adapterPosition == itemCount - 1,
                { viewModel?.addPoint(holder.adapterPosition, Match.WhichPlayer.HOST) },
                { viewModel?.addPoint(holder.adapterPosition, Match.WhichPlayer.GUEST) },
                { viewModel?.deleteMatch(holder.adapterPosition) }
            )
        }
    }
}
