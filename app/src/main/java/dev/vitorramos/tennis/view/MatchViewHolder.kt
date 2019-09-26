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

class MatchViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val cvItemMatch: CardView = v.findViewById(R.id.cv_match_layout)

    private val tvStarted: AppCompatTextView = v.findViewById(R.id.tv_match_started)
    private val ibDelete: AppCompatImageButton = v.findViewById(R.id.ib_match_delete)

    private val tvHostName: AppCompatTextView = v.findViewById(R.id.tv_match_host_name)
    private val tvGuestName: AppCompatTextView = v.findViewById(R.id.tv_match_guest_name)

    private val tvHostPoints: AppCompatTextView = v.findViewById(R.id.tv_match_host_points)
    private val tvGuestPoints: AppCompatTextView = v.findViewById(R.id.tv_match_guest_points)

    private val tvHostGames: AppCompatTextView = v.findViewById(R.id.tv_match_host_games)
    private val tvGuestGames: AppCompatTextView = v.findViewById(R.id.tv_match_guest_games)

    private val tvHostSets: AppCompatTextView = v.findViewById(R.id.tv_match_host_sets)
    private val tvGuestSets: AppCompatTextView = v.findViewById(R.id.tv_match_guest_sets)

    private val btHostAddPoint: AppCompatButton = v.findViewById(R.id.bt_match_host_add_point)
    private val btGuestAddPoint: AppCompatButton = v.findViewById(R.id.bt_match_guest_add_point)

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
