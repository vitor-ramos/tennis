package dev.vitorramos.tennis

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import dev.vitorramos.tennis.Game.Companion.MAP_FIELD_GAMES
import dev.vitorramos.tennis.Game.Companion.MAP_FIELD_POINTS
import dev.vitorramos.tennis.Game.Companion.MAP_FIELD_SETS
import dev.vitorramos.tennis.MainActivity.Companion.EXTRA_FIELD_GAME_ID
import dev.vitorramos.tennis.db.TheDatabase
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {
    private var hostName = ""
    private var guestName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val gameId = intent.getLongExtra(EXTRA_FIELD_GAME_ID, -1)
        if (gameId != -1L) {
            GlobalScope.launch {
                val gameDao = TheDatabase.db(applicationContext).gameDao()
                val gameEntity = gameDao.getGame(gameId)
                gameEntity?.let {
                    hostName = it.hostName
                    guestName = it.guestName

                    game_host_name.text = hostName
                    game_guest_name.text = guestName

                    val game = Game(it.gamesToSet, it.setsToMatch, onScoreChanged, onMatchFinished)
                    game_host_layout.setOnClickListener {
                        game.addPoint(WhichPlayer.HOST)
                    }
                    game_guest_layout.setOnClickListener {
                        game.addPoint(WhichPlayer.GUEST)
                    }
                }
            }
        }
    }

    private val onScoreChanged =
        { hostPoints: HashMap<String, String>, guestPoints: HashMap<String, String> ->
            game_host_points.text = hostPoints[MAP_FIELD_POINTS]
            game_host_games.text = hostPoints[MAP_FIELD_GAMES]
            game_host_sets.text = hostPoints[MAP_FIELD_SETS]

            game_guest_points.text = guestPoints[MAP_FIELD_POINTS]
            game_guest_games.text = guestPoints[MAP_FIELD_GAMES]
            game_guest_sets.text = guestPoints[MAP_FIELD_SETS]
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
}
