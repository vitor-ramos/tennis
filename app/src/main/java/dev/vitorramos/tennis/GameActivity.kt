package dev.vitorramos.tennis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dev.vitorramos.tennis.Game.Companion.MAP_FIELD_GAMES
import dev.vitorramos.tennis.Game.Companion.MAP_FIELD_POINTS
import dev.vitorramos.tennis.Game.Companion.MAP_FIELD_SETS
import dev.vitorramos.tennis.MainActivity.Companion.EXTRA_FIELD_GAMES
import dev.vitorramos.tennis.MainActivity.Companion.EXTRA_FIELD_GUEST
import dev.vitorramos.tennis.MainActivity.Companion.EXTRA_FIELD_HOST
import dev.vitorramos.tennis.MainActivity.Companion.EXTRA_FIELD_SETS
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val hostName = intent.getStringExtra(EXTRA_FIELD_HOST)
        val guestName = intent.getStringExtra(EXTRA_FIELD_GUEST)
        val gamesToWin = intent.getIntExtra(EXTRA_FIELD_GAMES, -1)
        val setsToWin = intent.getIntExtra(EXTRA_FIELD_SETS, -1)

        host_name.text = if(hostName != "") hostName else getString(R.string.you)
        guest_name.text = if(guestName != "") guestName else getString(R.string.guest)

        val game = Game(hostName, guestName, gamesToWin, setsToWin, onScoreChanged, onMatchFinished)
        host_add_point.setOnClickListener {
            game.addPoint(Game.WhichPlayer.HOST)
        }
        guest_add_point.setOnClickListener {
            game.addPoint(Game.WhichPlayer.GUEST)
        }
    }

    private val onScoreChanged = { hostPoints: HashMap<String, String>, guestPoints: HashMap<String, String> ->
        host_points.text = hostPoints[MAP_FIELD_POINTS]
        host_games.text = hostPoints[MAP_FIELD_GAMES]
        host_sets.text = hostPoints[MAP_FIELD_SETS]

        guest_points.text = guestPoints[MAP_FIELD_POINTS]
        guest_games.text = guestPoints[MAP_FIELD_GAMES]
        guest_sets.text = guestPoints[MAP_FIELD_SETS]
    }

    private val onMatchFinished = {
        Toast.makeText(this, "Match over!", Toast.LENGTH_SHORT).show()
    }
}
