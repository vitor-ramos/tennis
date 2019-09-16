package dev.vitorramos.tennis.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.getFormattedDate
import dev.vitorramos.tennis.getFormattedPoints
import dev.vitorramos.tennis.viewModel.MatchViewModel
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.dialog_start_match.*
import kotlinx.android.synthetic.main.item_match_guest.*
import kotlinx.android.synthetic.main.item_match_host.*

class MatchActivity : AppCompatActivity() {
    private var viewModel: MatchViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        viewModel?.onMatchIdLoaded(intent.extras?.getLong(EXTRA_MATCH_ID))
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

        viewModel?.deleteObservable()?.observe(this, Observer {
            if (it != null && it) showDelete()
        })

        viewModel?.finishObservable()?.observe(this, Observer {
            if (it != null && it) finish()
        })

        viewModel?.startMatchObservable()?.observe(this, Observer {
            if (it != null && it) showStart()
        })
    }

    private fun showDelete() {
        if (viewModel == null) return

        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.delete_confirmation_title))
            setMessage(getString(R.string.delete_confirmation_message))
            viewModel?.let { viewModel ->
                setPositiveButton(getString(R.string.delete), viewModel.onDelete)
                setNegativeButton(getString(R.string.cancel), viewModel.onDelete)
            }
        }.show()
    }

    private fun showStart() {
        android.app.AlertDialog.Builder(this).apply {
            setView(
                LayoutInflater.from(this@MatchActivity).inflate(
                    R.layout.dialog_start_match,
                    ll_match_root as LinearLayout,
                    false
                )
            )
            setTitle(getString(R.string.start_match))
            setPositiveButton(getString(R.string.start)) { dialog, _ ->
                viewModel?.onStart?.let {
                    it(
                        dialog,
                        et_start_match_host_name.text.toString(),
                        et_start_match_guest_name.text.toString(),
                        et_start_match_games_count.text.toString()
                    )
                }
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
