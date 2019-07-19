package dev.vitorramos.tennis.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.viewModel.HistoryViewModel
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        viewModel = ViewModelProviders.of(this)[HistoryViewModel::class.java]

        rv_history_content.layoutManager = LinearLayoutManager(this)
        rv_history_content.adapter = HistoryAdapter(layoutInflater)

        viewModel.matches.observe(this, Observer { array ->
            if (array != null) (rv_history_content.adapter as HistoryAdapter?)?.content = array
        })
    }
}
