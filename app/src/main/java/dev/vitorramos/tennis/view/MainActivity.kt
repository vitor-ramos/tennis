package dev.vitorramos.tennis.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]

        rv_history_content.layoutManager = LinearLayoutManager(this)
        rv_history_content.adapter = MainAdapter(viewModel)

        fab_history_start_match.setOnClickListener {
            viewModel?.onClickStart()
        }

        prepareObservables()
    }

    private fun prepareObservables() {
        viewModel?.matches?.observe(this, Observer {
            it?.let {
                (rv_history_content.adapter as MainAdapter).content = it
            }
        })

        viewModel?.dialogObservable?.observe(this, Observer { onStartGame ->
            @SuppressLint("InflateParams")
            val v = layoutInflater.inflate(R.layout.dialog_start_match, null, false)
            AlertDialog.Builder(this).apply {
                setTitle(R.string.start_match)
                setView(v)
                setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
                setPositiveButton(R.string.start) { dialog, _ ->
                    dialog.dismiss()
                    val etHostName =
                        v.findViewById<AppCompatEditText>(R.id.et_start_match_host_name)
                    val etGuestName =
                        v.findViewById<AppCompatEditText>(R.id.et_start_match_guest_name)
                    val etGamesCount =
                        v.findViewById<AppCompatEditText>(R.id.et_start_match_games_count)

                    onStartGame(
                        etHostName.text?.toString() ?: "",
                        etGuestName.text?.toString() ?: "",
                        etGamesCount.text?.toString() ?: ""
                    )
                }
            }.create().show()
        })
    }
}
