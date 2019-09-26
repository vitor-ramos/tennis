package dev.vitorramos.tennis.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var content: Array<MatchEntity?> = arrayOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MatchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_current,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        content[position]?.let {
            (holder as MatchViewHolder).bind(it, position == itemCount - 1)
        }
    }
}
