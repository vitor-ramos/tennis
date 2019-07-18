package dev.vitorramos.tennis.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.TennisApplication
import dev.vitorramos.tennis.viewModel.HistoryViewModel

class HistoryActivity : AppCompatActivity() {
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        viewModel = ViewModelProviders.of(this)[HistoryViewModel::class.java]
        viewModel.tennisRepository = (application as TennisApplication).tennisRepository

        viewModel.matches.observe(this, Observer {
            it?.let {
                it.forEach {
                    it?.let {
                        println(it)
                    }
                }
            }
        })
    }
}
