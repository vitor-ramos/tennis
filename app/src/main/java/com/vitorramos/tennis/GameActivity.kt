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

        val game = Game(oneName, twoName, games, sets, onMatchFinished)
        one_add_point.setOnClickListener {
            val hashMap = game.addPoint(Game.PlayerType.HOST)
            one_points.text = hashMap["points"]
            one_games.text = hashMap["games"]
            one_sets.text = hashMap["sets"]
        }
        two_add_point.setOnClickListener {
            val hashMap = game.addPoint(Game.PlayerType.GUEST)
            two_points.text = hashMap["points"]
            two_games.text = hashMap["games"]
            two_sets.text = hashMap["sets"]
        }
    }

    private val onMatchFinished = {
        Toast.makeText(this, "Match over!", Toast.LENGTH_SHORT).show()
    }
}
