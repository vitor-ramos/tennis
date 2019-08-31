package dev.vitorramos.tennis.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.PREF_FIELD_MATCH_ID
import dev.vitorramos.tennis.PREF_FILE_NAME
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.getFormattedPoints
import dev.vitorramos.tennis.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bt_home_history.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        val viewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]

        bt_home_start_match.setOnClickListener {
            startActivity(Intent(this, StartMatchActivity::class.java))
        }

        getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE).getLong(PREF_FIELD_MATCH_ID, -1).also { matchId ->
            if (matchId != -1L) {
                viewModel.currentMatch(matchId).observe(this, Observer {
                    if (it != null) onMatchLoaded(it)
                })
            }
        }
    }

    private fun onMatchLoaded(matchEntity: MatchEntity) {
        if (matchEntity.id == null) return

        populateTextViews(matchEntity)
        bt_home_resume_match.setOnClickListener {
            getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE).edit().apply {
                putLong(PREF_FIELD_MATCH_ID, matchEntity.id!!)
                apply()
            }
            startActivity(Intent(this, MatchActivity::class.java))
        }
    }

    private fun populateTextViews(matchEntity: MatchEntity) {
        home_current_host_name.text = matchEntity.hostName
        home_current_host_points.text = getFormattedPoints(matchEntity.hostPoints)
        home_current_host_games.text = matchEntity.hostGames.toString()
        home_current_host_sets.text = matchEntity.hostSets.toString()

        home_current_guest_name.text = matchEntity.guestName
        home_current_guest_points.text = getFormattedPoints(matchEntity.guestPoints)
        home_current_guest_games.text = matchEntity.guestGames.toString()
        home_current_guest_sets.text = matchEntity.guestSets.toString()
    }
}
