package com.vitorramos.tennis

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_match.setOnClickListener {
            val hostName = host_name.text?.toString() ?: ""
            val guestName = guest_name.text?.toString() ?: ""
            val games = games_count.text?.toString()?.toInt() ?: 3
            val sets = sets_count.text?.toString()?.toInt() ?: 2

            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(EXTRA_FIELD_HOST, if (!hostName.isEmpty()) hostName else getString(R.string.you))
            intent.putExtra(EXTRA_FIELD_GUEST, if (!guestName.isEmpty()) guestName else getString(R.string.guest))
            intent.putExtra(EXTRA_FIELD_GAMES, games)
            intent.putExtra(EXTRA_FIELD_SETS, sets)
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_FIELD_HOST = "hostName"
        const val EXTRA_FIELD_GUEST = "guestName"
        const val EXTRA_FIELD_GAMES = "games"
        const val EXTRA_FIELD_SETS = "sets"
    }
}
