package dev.vitorramos.tennis.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.getFormattedDate
import dev.vitorramos.tennis.getFormattedPoints
import dev.vitorramos.tennis.viewModel.MatchViewModel
import kotlinx.android.synthetic.main.item_match_guest.*
import kotlinx.android.synthetic.main.item_match_host.*

class MatchActivity : AppCompatActivity() {
    private var viewModel: MatchViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        prepareObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_match, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return viewModel?.onOptionItemSelected(item.itemId) ?: false
    }

    private fun prepareObservers() {
        intent.extras?.getLong(EXTRA_MATCH_ID)?.let { matchId ->
            viewModel?.matchId = matchId
            viewModel?.currentMatch?.observe(this, Observer {
                it?.let {
                    val hostName = if (it.hostName != "") it.hostName else getString(R.string.you)
                    val guestName =
                        if (it.guestName != "") it.guestName else getString(R.string.guest)

                    title = "${getString(R.string.match_label)} ${getFormattedDate(
                        resources,
                        it.started
                    )}"

                    tv_match_host_name.text = hostName
                    tv_match_host_points.text = getFormattedPoints(it.hostPoints)
                    tv_match_host_games.text = it.hostGames.toString()
                    tv_match_host_sets.text = it.hostSets.toString()

                    tv_match_guest_name.text = guestName
                    tv_match_guest_points.text = getFormattedPoints(it.guestPoints)
                    tv_match_guest_games.text = it.guestGames.toString()
                    tv_match_guest_sets.text = it.guestSets.toString()
                }
            })
        }

        viewModel?.dialogObservable()?.observe(this, Observer {
            if (it != null && it) showDialog()
        })

        viewModel?.finishObservable()?.observe(this, Observer {
            if (it != null && it) finish()
        })
    }

    private fun showDialog() {
        if (viewModel == null) return

        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.delete_confirmation_title))
            setMessage(getString(R.string.delete_confirmation_message))
            viewModel?.let { viewModel ->
                setPositiveButton(getString(R.string.delete), viewModel.onDialogClickListener)
                setNegativeButton(getString(R.string.cancel), viewModel.onDialogClickListener)
            }
        }.show()
    }

    fun onClick(v: View) {
        viewModel?.onClick(v.id)
    }

    companion object {
        const val EXTRA_MATCH_ID = "EXTRA_MATCH_ID"
    }
}
