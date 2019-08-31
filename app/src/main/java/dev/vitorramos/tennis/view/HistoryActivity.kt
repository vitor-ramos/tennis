package dev.vitorramos.tennis.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.viewModel.HistoryViewModel
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val viewModel = ViewModelProviders.of(this)[HistoryViewModel::class.java]

        rv_history_content.layoutManager = LinearLayoutManager(this)

        val adapter = HistoryAdapter(layoutInflater) { itemPosition ->
            startActivity(Intent(this, MatchActivity::class.java).apply {
                viewModel.matches.value?.get(itemPosition)?.let {
                    putExtra(MatchActivity.EXTRA_MATCH_ID, it.id)
                }
            })
        }
        rv_history_content.adapter = adapter

        viewModel.matches.observe(this, Observer {
            it?.let {
                adapter.content = it
            }
        })

        fab_history_start_match.setOnClickListener {
            startActivity(Intent(this, StartMatchActivity::class.java))
        }
    }
}
