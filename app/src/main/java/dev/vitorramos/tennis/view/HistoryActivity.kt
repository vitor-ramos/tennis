package dev.vitorramos.tennis.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_MATCH_ACTIVITY) {
            if (resultCode == RESULT_OK && data?.getSerializableExtra(MATCH_DELETED) != null) {
                Snackbar.make(cl_history, getString(R.string.match_deleted), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo)) {
                        viewModel?.undoDeletion(data.getSerializableExtra(MATCH_DELETED) as MatchEntity)
                    }.show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        const val MATCH_DELETED = "MATCH_DELETED"
        const val RC_MATCH_ACTIVITY = 1
    }
}
