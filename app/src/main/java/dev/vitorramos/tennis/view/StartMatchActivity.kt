package dev.vitorramos.tennis.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.viewModel.StartMatchViewModel
import kotlinx.android.synthetic.main.activity_start_match.*

class StartMatchActivity : AppCompatActivity() {
    private var viewModel: StartMatchViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_match)

        viewModel = ViewModelProviders.of(this)[StartMatchViewModel::class.java]
        et_start_match_confirm.setOnClickListener(onConfirmListener)
    }

    private val onConfirmListener: (View) -> Unit = {
        val gamesInput = et_start_match_games_count.text?.toString() ?: ""
        val games = if (gamesInput != "") gamesInput.toInt() else resources.getInteger(R.integer.default_games)

        val setsInput = et_start_match_sets_count.text?.toString() ?: ""
        val sets = if (setsInput != "") setsInput.toInt() else resources.getInteger(R.integer.default_sets)

        val hostNameInput = et_start_match_host_name.text?.toString() ?: ""
        val hostName = if (hostNameInput != "") hostNameInput else getString(R.string.you)

        val guestNameInput = et_start_match_guest_name.text?.toString() ?: ""
        val guestName =
            if (guestNameInput != "") guestNameInput else getString(R.string.guest_name)

        startMatch(games, sets, hostName, guestName)
    }

    private fun startMatch(games: Int, sets: Int, hostName: String, guestName: String) {
        viewModel?.startMatch(games, sets, hostName, guestName) { matchId ->
            finish()
            startActivity(Intent(this, MatchActivity::class.java).apply {
                putExtra(MatchActivity.EXTRA_MATCH_ID, matchId)
            })
        }
    }
}
