package dev.vitorramos.tennis.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.PREF_FIELD_MATCH_ID
import dev.vitorramos.tennis.PREF_FILE_NAME
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.getFormattedPoints
import dev.vitorramos.tennis.viewModel.MatchViewModel
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {
    private var hostName = ""
    private var guestName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE).getLong(PREF_FIELD_MATCH_ID, -1)
            .also { matchId ->
                if (matchId == -1L) finish()

                viewModel.matchId = matchId
                viewModel.currentMatch.observe(this, Observer {
                    it?.let {
                        hostName = if (it.hostName != "") it.hostName else getString(R.string.you)
                        guestName =
                            if (it.guestName != "") it.guestName else getString(R.string.guest_name)

                        match_host_name.text = hostName
                        match_host_points.text = getFormattedPoints(it.hostPoints)
                        match_host_games.text = it.hostGames.toString()
                        match_host_sets.text = it.hostSets.toString()

                        match_guest_name.text = guestName
                        match_guest_points.text = getFormattedPoints(it.guestPoints)
                        match_guest_games.text = it.guestGames.toString()
                        match_guest_sets.text = it.guestSets.toString()
                    }
                })
            }

        bt_match_host_add_point.setOnClickListener {
            viewModel.addHostPoint()
        }
        bt_match_guest_add_point.setOnClickListener {
            viewModel.addGuestPoint()
        }
    }
}
