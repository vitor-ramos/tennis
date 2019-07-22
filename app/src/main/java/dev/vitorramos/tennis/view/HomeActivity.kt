package dev.vitorramos.tennis.view

import android.content.Intent
import android.os.Bundle
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.PREF_FIELD_MATCH_ID
import dev.vitorramos.tennis.PREF_FILE_NAME
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]

        viewModel.onGoingMatch.observe(this, Observer { isThereAGame ->
            if (isThereAGame) {
                bt_home_start_match.setOnClickListener {
                    AlertDialog.Builder(this).apply {
                        setCancelable(false)
                        setTitle("Partida em Andamento")
                        setMessage("Por favor, encerre a partida atual antes de iniciar outra")
                        setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        show()
                    }
                }
            } else {
                bt_home_start_match.setOnClickListener {
                    startActivity(Intent(this, StartMatchActivity::class.java))
                }
            }
        })

        getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE).getLong(PREF_FIELD_MATCH_ID, -1).also { matchId ->
            if (matchId != -1L) viewModel.currentMatch(matchId).observe(this, Observer {
                if (it != null) {
                    home_current_host_name.text = it.hostName
                    home_current_host_points.text = it.hostPoints.toString()
                    home_current_host_games.text = it.hostGames.toString()
                    home_current_host_sets.text = it.hostSets.toString()

                    home_current_guest_points.text = it.guestPoints.toString()
                    home_current_guest_games.text = it.guestGames.toString()
                    home_current_guest_sets.text = it.guestSets.toString()
                    home_current_guest_name.text = it.guestName
                }
            })
        }

        bt_home_history.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}
