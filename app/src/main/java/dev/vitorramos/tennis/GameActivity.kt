package dev.vitorramos.tennis

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.MainActivity.Companion.EXTRA_FIELD_GAME_ID
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    private var hostName = ""
    private var guestName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val gameId = intent.getLongExtra(EXTRA_FIELD_GAME_ID, -1)
        if (gameId == -1L) return

        val model = ViewModelProviders.of(this).get(GameViewModel::class.java)
        model.getGame().observe(this, Observer {
            if(it != null) {
                game_host_points.text = getFormattedPoints(it.hostPoints)
                game_host_games.text = it.hostGames.toString()
                game_host_sets.text = it.hostSets.toString()

                game_guest_points.text = getFormattedPoints(it.guestPoints)
                game_guest_games.text = it.guestGames.toString()
                game_guest_sets.text = it.guestSets.toString()
            }
        })

        game_host_layout.setOnClickListener {
            model.addHostPoint()
        }
        game_guest_layout.setOnClickListener {
            model.addGuestPoint()
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
