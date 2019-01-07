package com.vitorramos.tennis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val oneName = intent.getStringExtra("oneName")
        val twoName = intent.getStringExtra("twoName")
        val games = intent.getIntExtra("games", -1)
        val sets = intent.getIntExtra("sets", -1)

        val game = Game(oneName, twoName, games, sets, onScoreChanged, onMatchFinished)
        one_add_point.setOnClickListener {
            game.addPoint(Game.PlayerType.HOST)
        }
        two_add_point.setOnClickListener {
            game.addPoint(Game.PlayerType.GUEST)
        }
    }

    private val onScoreChanged: (HashMap<String, String>, HashMap<String, String>) -> Unit = { onePoints, twoPoints ->
        one_points.text = onePoints["points"]
        one_games.text = onePoints["games"]
        one_sets.text = onePoints["sets"]

        two_points.text = twoPoints["points"]
        two_games.text = twoPoints["games"]
        two_sets.text = twoPoints["sets"]
    }

    private val onMatchFinished = {
        Toast.makeText(this, "Match over!", Toast.LENGTH_SHORT).show()
    }
}
