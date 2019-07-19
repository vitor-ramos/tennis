package dev.vitorramos.tennis.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.EXTRA_FIELD_MATCH_ID
import dev.vitorramos.tennis.PREF_FIELD_MATCH_ID
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.PREF_FILE_NAME
import dev.vitorramos.tennis.viewModel.StartMatchViewModel
import kotlinx.android.synthetic.main.activity_start_match.*
import java.util.*

class StartMatchActivity : AppCompatActivity() {
    private lateinit var viewModel: StartMatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_match)

        viewModel = ViewModelProviders.of(this)[StartMatchViewModel::class.java]
        initializeViews()
    }

    private fun initializeViews() {
        start_match_start_match.setOnClickListener {
            val hostNameInput = start_match_host_name.text?.toString() ?: ""
            val hostName = if (hostNameInput != "") hostNameInput else getString(R.string.you)

            val guestNameInput = start_match_guest_name.text?.toString() ?: ""
            val guestName =
                if (guestNameInput != "") guestNameInput else getString(R.string.guest_name)

            val gamesInput = start_match_games_count.text?.toString() ?: ""
            val games = if (gamesInput != "") gamesInput.toInt() else 3

            val setsInput = start_match_sets_count.text?.toString() ?: ""
            val sets = if (setsInput != "") setsInput.toInt() else 2

            viewModel.startMatch(
                started = Calendar.getInstance().timeInMillis,
                gamesToSet = games,
                setsToMatch = sets,
                hostName = hostName,
                guestName = guestName
            ) { matchId ->
                getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE).edit().apply {
                    putLong(PREF_FIELD_MATCH_ID, matchId)
                    apply()
                }

                startActivity(with(
                    Intent(
                        this@StartMatchActivity,
                        MatchActivity::class.java
                    )
                ) {
                    putExtra(EXTRA_FIELD_MATCH_ID, matchId)
                })
            }
        }
    }
}
