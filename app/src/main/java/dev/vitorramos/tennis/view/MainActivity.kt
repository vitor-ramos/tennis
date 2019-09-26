package dev.vitorramos.tennis.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            viewModel?.onClickStart(this)
        }

        prepareObservables()
    }

    private fun prepareObservables() {
        viewModel?.matches?.observe(this, Observer {
            it?.let {
                (rv_history_content.adapter as MainAdapter).content = it
            }
        })
    }
}
