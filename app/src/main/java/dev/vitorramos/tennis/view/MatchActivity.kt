package dev.vitorramos.tennis.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.Match.WhichPlayer
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.view.StartMatchActivity.Companion.EXTRA_FIELD_MATCH_ID
import dev.vitorramos.tennis.viewModel.MatchViewModel
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {
    private var hostName = ""
    private var guestName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val matchId = intent.getLongExtra(EXTRA_FIELD_MATCH_ID, -1)
        if (matchId == -1L) return

        val viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        viewModel.matchId = matchId
        viewModel.currentMatch.observe(this, Observer {
            if (it != null) {
                if (it.ended != null) onMatchFinished(if (it.winner == 0) WhichPlayer.HOST else WhichPlayer.GUEST)
                else {
                    hostName = if (it.hostName != "") it.hostName else getString(R.string.you)
                    guestName = if (it.guestName != "") it.guestName else getString(R.string.guest_name)

                    game_host_name.text = hostName
                    game_host_points.text = getFormattedPoints(it.hostPoints)
                    game_host_games.text = it.hostGames.toString()
                    game_host_sets.text = it.hostSets.toString()

                    game_guest_name.text = guestName
                    game_guest_points.text = getFormattedPoints(it.guestPoints)
                    game_guest_games.text = it.guestGames.toString()
                    game_guest_sets.text = it.guestSets.toString()
                }
            }
        })

        game_host_layout.setOnClickListener {
            viewModel.addHostPoint()
        }
        game_guest_layout.setOnClickListener {
            viewModel.addGuestPoint()
        }
    }

    private val onMatchFinished = { winner: WhichPlayer ->
        with(AlertDialog.Builder(this)) {
            setCancelable(false)
            setTitle(getString(R.string.match_over))
            val winnerName = if (winner == WhichPlayer.HOST) hostName else guestName
            setMessage("$winnerName ${getString(R.string.won)}")
            setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            create().show()
        }
        Unit
    }


    companion object {
        private fun getFormattedPoints(points: Int): String {
            return when (points) {
                0 -> "0"
                1 -> "15"
                2 -> "30"
                3 -> "40"
                4 -> "A"
                else -> ""
            }
        }
    }
}
