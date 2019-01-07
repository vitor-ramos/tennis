package com.vitorramos.tennis

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_match.setOnClickListener {
            val oneName = one_name.text?.toString() ?: ""
            val twoName = two_name.text?.toString() ?: ""
            val games = games_count.text?.toString()?.toInt() ?: 3
            val sets = sets_count.text?.toString()?.toInt() ?: 2

            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("oneName", if (!oneName.isEmpty()) oneName else "Você")
            intent.putExtra("twoName", if (!twoName.isEmpty()) twoName else "Convidado")
            intent.putExtra("games", games)
            intent.putExtra("sets", sets)
            startActivity(intent)
        }
    }
}
