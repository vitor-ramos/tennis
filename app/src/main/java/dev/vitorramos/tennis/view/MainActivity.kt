package dev.vitorramos.tennis.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.viewModel.HistoryViewModel
import kotlinx.android.synthetic.main.activity_history.*

class MainActivity : AppCompatActivity() {
    private var viewModel: HistoryViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        viewModel = ViewModelProviders.of(this)[HistoryViewModel::class.java]

        rv_history_content.layoutManager = LinearLayoutManager(this)
        rv_history_content.adapter = adapter

        prepareObservables()
    }

    private val adapter by lazy {
        // TODO: send state
        MainAdapter(MainAdapter.HistoryState.NO_MATCH)
    }

    private fun prepareObservables() {
        viewModel?.matches?.observe(this, Observer {
            it?.let {
                (rv_history_content.adapter as MainAdapter).content = it
            }
        })
    }
}
