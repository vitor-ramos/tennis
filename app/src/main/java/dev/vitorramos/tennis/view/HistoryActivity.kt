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
    private var viewModel: HistoryViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        viewModel = ViewModelProviders.of(this)[HistoryViewModel::class.java]

        rv_history_content.layoutManager = LinearLayoutManager(this)
        rv_history_content.adapter = HistoryAdapter(layoutInflater,
            {
                startActivity(Intent(this, StartMatchActivity::class.java))
            },
            { itemPosition ->
                startActivityForResult(Intent(this, MatchActivity::class.java).apply {
                    viewModel?.matches?.value?.get(itemPosition)?.let {
                        putExtra(MatchActivity.EXTRA_MATCH_ID, it.id)
                    }
                }, RC_MATCH_ACTIVITY)
            })

        viewModel?.matches?.observe(this, Observer {
            it?.let {
                (rv_history_content.adapter as HistoryAdapter).content = it
            }
        })
    }

    companion object {
        const val RC_MATCH_ACTIVITY = 1
    }
}
