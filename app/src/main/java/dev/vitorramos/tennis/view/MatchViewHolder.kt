package dev.vitorramos.tennis.view

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.databinding.ItemMatchBinding
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.getFormattedDate
import dev.vitorramos.tennis.presenter.MatchPresenter

class MatchViewHolder(private val binding: ItemMatchBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        resources: Resources,
        matchEntity: MatchEntity,
        isLast: Boolean,
        onHostClick: () -> Unit,
        onGuestClick: () -> Unit,
        onDelete: () -> Unit
    ) {
        binding.match = with(matchEntity) {
            MatchPresenter(
                getFormattedDate(resources, started),
                hostName,
                hostPoints.toString(),
                hostGames.toString(),
                hostSets.toString(),
                guestName,
                guestPoints.toString(),
                guestSets.toString(),
                guestSets.toString(),
                isLast,
                { onHostClick() },
                { onGuestClick() },
                { onDelete() }
            )
        }
        binding.executePendingBindings()
    }
}
