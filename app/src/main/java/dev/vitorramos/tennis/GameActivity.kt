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
        val gamesToSet = intent.getIntExtra(EXTRA_FIELD_GAMES, -1)
        val setsToMatch = intent.getIntExtra(EXTRA_FIELD_SETS, -1)

        game_host_name.text = hostName
        game_guest_name.text = guestName

        with(Game(gamesToSet, setsToMatch, onScoreChanged, onMatchFinished)) {
            game_host_layout.setOnClickListener {
                addPoint(Game.WhichPlayer.HOST)
            }
            game_guest_layout.setOnClickListener {
                addPoint(Game.WhichPlayer.GUEST)
            }
        }
    }

    private val onScoreChanged = { hostPoints: HashMap<String, String>, guestPoints: HashMap<String, String> ->
        game_host_points.text = hostPoints[MAP_FIELD_POINTS]
        game_host_games.text = hostPoints[MAP_FIELD_GAMES]
        game_host_sets.text = hostPoints[MAP_FIELD_SETS]

        game_guest_points.text = guestPoints[MAP_FIELD_POINTS]
        game_guest_games.text = guestPoints[MAP_FIELD_GAMES]
        game_guest_sets.text = guestPoints[MAP_FIELD_SETS]
    }

    private val onMatchFinished = {
        Toast.makeText(this, getString(R.string.match_over), Toast.LENGTH_SHORT).show()
    }
}
