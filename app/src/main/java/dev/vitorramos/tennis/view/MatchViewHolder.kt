package dev.vitorramos.tennis.view

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.getFormattedDate
import dev.vitorramos.tennis.getFormattedPoints

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cvItemMatch: CardView = itemView.findViewById(R.id.cv_current_layout)

    private val tvStarted: AppCompatTextView = itemView.findViewById(R.id.tv_current_started)
    private val ibDelete: AppCompatImageButton = itemView.findViewById(R.id.ib_current_delete)

    private val tvHostName: AppCompatTextView = itemView.findViewById(R.id.tv_current_host_name)
    private val tvGuestName: AppCompatTextView = itemView.findViewById(R.id.tv_current_guest_name)

    private val tvHostPoints: AppCompatTextView = itemView.findViewById(R.id.tv_current_host_points)
    private val tvGuestPoints: AppCompatTextView =
        itemView.findViewById(R.id.tv_current_guest_points)

    private val tvHostGames: AppCompatTextView = itemView.findViewById(R.id.tv_current_host_games)
    private val tvGuestGames: AppCompatTextView = itemView.findViewById(R.id.tv_current_guest_games)

    private val tvHostSets: AppCompatTextView = itemView.findViewById(R.id.tv_current_host_sets)
    private val tvGuestSets: AppCompatTextView = itemView.findViewById(R.id.tv_current_guest_sets)

    private val btHostAddPoint: AppCompatButton =
        itemView.findViewById(R.id.bt_current_host_add_point)
    private val btGuestAddPoint: AppCompatButton =
        itemView.findViewById(R.id.bt_current_guest_add_point)

    fun bind(
        matchEntity: MatchEntity,
        isLast: Boolean,
        onHostClick: () -> Unit,
        onGuestClick: () -> Unit,
        onDelete: () -> Unit
    ) {
        tvHostName.text = matchEntity.hostName
        tvGuestName.text = matchEntity.guestName

        tvHostPoints.text = getFormattedPoints(matchEntity.hostPoints)
        tvGuestPoints.text = getFormattedPoints(matchEntity.guestPoints)

        tvHostGames.text = matchEntity.hostGames.toString()
        tvGuestGames.text = matchEntity.guestGames.toString()

        tvHostSets.text = matchEntity.hostSets.toString()
        tvGuestSets.text = matchEntity.guestSets.toString()

        tvStarted.text = getFormattedDate(
            itemView.resources,
            matchEntity.started
        )

        btHostAddPoint.setOnClickListener { onHostClick() }

        btGuestAddPoint.setOnClickListener { onGuestClick() }

        ibDelete.setOnClickListener {
            onDelete()
        }

        cvItemMatch.layoutParams =
            (cvItemMatch.layoutParams as ViewGroup.MarginLayoutParams).apply {
                val marginBottom = if (isLast) {
                    itemView.context.resources
                        .getDimension(R.dimen.margin_bottom_list_fab)
                } else {
                    itemView.context.resources
                        .getDimension(R.dimen.default_margin)
                }
                setMargins(
                    leftMargin,
                    topMargin,
                    rightMargin,
                    marginBottom.toInt()
                )
            }
    }
}
