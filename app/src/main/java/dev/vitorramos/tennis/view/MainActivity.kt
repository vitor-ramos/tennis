package dev.vitorramos.tennis.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        initializeViews()
    }

    private fun initializeViews() {
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

            viewModel.startMatch(
                started = Calendar.getInstance().timeInMillis,
                gamesToSet = games,
                setsToMatch = sets,
                hostName = hostName,
                guestName = guestName
            ) { matchId ->
                startActivity(with(
                    Intent(
                        this@MainActivity,
                        MatchActivity::class.java
                    )
                ) {
                    putExtra(EXTRA_FIELD_MATCH_ID, matchId)
                })
            }
        }
    }

    companion object {
        const val EXTRA_FIELD_MATCH_ID = "matchId"
    }
}
