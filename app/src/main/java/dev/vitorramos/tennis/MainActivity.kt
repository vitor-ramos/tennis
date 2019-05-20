package dev.vitorramos.tennis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        start_match.setOnClickListener {
//            with(Intent(this, GameActivity::class.java)) {
//                putExtra(EXTRA_FIELD_HOST, host_name.text?.toString() ?: getString(R.string.you))
//                putExtra(EXTRA_FIELD_GUEST, guest_name.text?.toString() ?: getString(R.string.guest))
//                putExtra(EXTRA_FIELD_GAMES, games_count.text?.toString()?.toInt() ?: 3)
//                putExtra(EXTRA_FIELD_SETS, sets_count.text?.toString()?.toInt() ?: 2)
//                startActivity(this)
//            }
//        }
    }

    companion object {
        const val EXTRA_FIELD_HOST = "hostName"
        const val EXTRA_FIELD_GUEST = "guestName"
        const val EXTRA_FIELD_GAMES = "games"
        const val EXTRA_FIELD_SETS = "sets"
    }
}
