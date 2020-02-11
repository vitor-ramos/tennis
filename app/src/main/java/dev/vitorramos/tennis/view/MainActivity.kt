package dev.vitorramos.tennis.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.databinding.ActivityMainBinding
import dev.vitorramos.tennis.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val mainAdapter by lazy { MainAdapter().apply { vm = viewModel } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        binding.viewmodel = viewModel

        rv_history_content.layoutManager = LinearLayoutManager(this)
        rv_history_content.adapter = mainAdapter

        prepareObservables()
    }

    private fun prepareObservables() {
        viewModel.matches.observe(this, Observer {
            it?.let {
                mainAdapter.submitList(it)
            }
        })

        viewModel.dialogObservable.observe(this, Observer { onStartGame ->
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
