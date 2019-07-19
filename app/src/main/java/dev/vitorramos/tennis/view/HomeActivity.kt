package dev.vitorramos.tennis.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.vitorramos.tennis.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bt_home_start_match.setOnClickListener {
            startActivity(Intent(this, StartMatchActivity::class.java))
        }

        bt_home_history.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}
