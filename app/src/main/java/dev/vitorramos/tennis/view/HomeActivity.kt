package dev.vitorramos.tennis.view

import android.content.Intent
import android.os.Bundle
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.PREF_FIELD_MATCH_ID
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.PREF_FILE_NAME
import dev.vitorramos.tennis.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE).getLong(PREF_FIELD_MATCH_ID, -1).also { matchId ->
            if(matchId != -1L) viewModel.currentMatch(matchId).observe(this, Observer {
                if(it != null) {
                    home_current_host_name.text = it.hostName
                    home_current_host_points_label.text = it.hostPoints.toString()
                    home_current_host_games_label.text = it.hostGames.toString()
                    home_current_host_sets_label.text = it.hostSets.toString()

                    home_current_guest_points_label.text = it.guestPoints.toString()
                    home_current_guest_games_label.text = it.guestGames.toString()
                    home_current_guest_sets_label.text = it.guestSets.toString()
                    home_current_guest_name.text = it.guestName

                    home_current_host_layout.visibility = VISIBLE
                    home_current_guest_layout.visibility = VISIBLE
                }
            })
        }

        bt_home_start_match.setOnClickListener {
            startActivity(Intent(this, StartMatchActivity::class.java))
        }

        bt_home_history.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}
