package dev.vitorramos.tennis

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.vitorramos.tennis.db.TheDatabase
import dev.vitorramos.tennis.db.entity.MatchEntity
import dev.vitorramos.tennis.matchScreen.MatchActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_start_match.setOnClickListener {
            val hostNameInput = main_host_name.text?.toString() ?: ""
            val hostName = if (hostNameInput != "") hostNameInput else getString(R.string.you)

            val guestNameInput = main_guest_name.text?.toString() ?: ""
            val guestName =
                if (guestNameInput != "") guestNameInput else getString(R.string.guest_name)

            val gamesInput = main_games_count.text?.toString() ?: ""
            val games = if (gamesInput != "") gamesInput.toInt() else 3

            val setsInput = main_sets_count.text?.toString() ?: ""
            val sets = if (setsInput != "") setsInput.toInt() else 2

            GlobalScope.launch {
                val matchId = TheDatabase.db(applicationContext).matchDao().insertMatch(
                    MatchEntity(
                        started = Calendar.getInstance().timeInMillis,
                        gamesToSet = games,
                        setsToMatch = sets,
                        hostName = hostName,
                        guestName = guestName
                    )
                )

                startActivity(
                    with(
                        Intent(
                            this@MainActivity,
                            MatchActivity::class.java
                        )
                    ) {
                        putExtra(EXTRA_FIELD_GAME_ID, matchId)
                    })
            }
        }
    }

    companion object {
        const val EXTRA_FIELD_GAME_ID = "matchId"
    }
}
