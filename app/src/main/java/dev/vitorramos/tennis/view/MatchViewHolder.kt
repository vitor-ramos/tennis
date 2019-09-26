package dev.vitorramos.tennis.view

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.getFormattedDate

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cvItemMatch: CardView = itemView.findViewById(R.id.cv_current_layout)

    private val tvHostName: AppCompatTextView = itemView.findViewById(R.id.tv_current_host_name)
    private val tvGuestName: AppCompatTextView = itemView.findViewById(R.id.tv_current_guest_name)

    private val tvHostPoints: AppCompatTextView = itemView.findViewById(R.id.tv_current_host_points)
    private val tvGuestPoints: AppCompatTextView =
        itemView.findViewById(R.id.tv_current_guest_points)

    private val tvHostGames: AppCompatTextView = itemView.findViewById(R.id.tv_current_host_games)
    private val tvGuestGames: AppCompatTextView = itemView.findViewById(R.id.tv_current_guest_games)

    private val tvHostSets: AppCompatTextView = itemView.findViewById(R.id.tv_current_host_sets)
    private val tvGuestSets: AppCompatTextView = itemView.findViewById(R.id.tv_current_guest_sets)

    private val tvStarted: AppCompatTextView = itemView.findViewById(R.id.tv_current_started)

    fun bind(matchEntity: MatchEntity, isLast: Boolean) {
        tvHostName.text = matchEntity.hostName
        tvGuestName.text = matchEntity.guestName

        tvHostPoints.text = matchEntity.hostPoints.toString()
        tvGuestPoints.text = matchEntity.guestPoints.toString()

        tvHostGames.text = matchEntity.hostGames.toString()
        tvGuestGames.text = matchEntity.guestGames.toString()

        tvHostSets.text = matchEntity.hostSets.toString()
        tvGuestSets.text = matchEntity.guestSets.toString()

        tvStarted.text = getFormattedDate(
            itemView.resources,
            matchEntity.started
        )

        if (isLast) {
            cvItemMatch.layoutParams =
                (cvItemMatch.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    val defaultMargin = itemView.context.resources
                        .getDimension(R.dimen.margin_bottom_list_fab)
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
