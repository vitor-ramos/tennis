package dev.vitorramos.tennis

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_match.setOnClickListener {
            val hostNameInput = host_name.text?.toString() ?: ""
            val hostName = if(hostNameInput != "") hostNameInput else getString(R.string.you)

            val guestNameInput = host_name.text?.toString() ?: ""
            val guestName = if(guestNameInput != "") guestNameInput else getString(R.string.guest_name)

            val gamesInput = games_count.text?.toString() ?: ""
            val games = if(gamesInput != "") gamesInput.toInt() else 3

            val setsInput = sets_count.text?.toString() ?: ""
            val sets = if(setsInput != "") setsInput.toInt() else 2

            startActivity(with(Intent(this, GameActivity::class.java)) {
                putExtra(EXTRA_FIELD_HOST, hostName)
                putExtra(EXTRA_FIELD_GUEST, guestName)
                putExtra(EXTRA_FIELD_GAMES, games)
                putExtra(EXTRA_FIELD_SETS, sets)
            })
        }
    }

    companion object {
        const val EXTRA_FIELD_HOST = "hostName"
        const val EXTRA_FIELD_GUEST = "guestName"
        const val EXTRA_FIELD_GAMES = "games"
        const val EXTRA_FIELD_SETS = "sets"
    }
}
