package dev.vitorramos.tennis.viewModel

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.repository.Repository
import dev.vitorramos.tennis.view.MainAdapter.HistoryState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val matches by lazy { Repository.it?.getMatches() ?: MutableLiveData() }

    private val state = MutableLiveData<HistoryState>()
    fun state(): LiveData<HistoryState> = state

    @SuppressLint("InflateParams")
    fun onClickStart(context: Activity) {
        val v = context.layoutInflater.inflate(R.layout.dialog_start_match, null, false)
        AlertDialog.Builder(context).apply {
            setTitle(R.string.start_match)
            setView(v)
            setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            setPositiveButton(R.string.start) { dialog, _ ->
                dialog.dismiss()
                val etHostName = v.findViewById<AppCompatEditText>(R.id.et_start_match_host_name)
                val etGuestName = v.findViewById<AppCompatEditText>(R.id.et_start_match_guest_name)
                val etGamesCount =
                    v.findViewById<AppCompatEditText>(R.id.et_start_match_games_count)

                onStart(
                    etHostName.text?.toString() ?: "",
                    etGuestName.text?.toString() ?: "",
                    etGamesCount.text?.toString() ?: ""
                )
            }
        }.create().show()
    }

    private fun onStart(
        hostNameInput: String, guestNameInput: String, gamesInput: String
    ) {
        val games = if (gamesInput != "") {
            gamesInput.toInt()
        } else {
            getApplication<Application>().resources.getInteger(R.integer.default_games)
        }

        val hostName = if (hostNameInput != "") {
            hostNameInput
        } else {
            getApplication<Application>().getString(R.string.you)
        }

        val guestName = if (guestNameInput != "") {
            guestNameInput
        } else {
            getApplication<Application>().getString(R.string.guest)
        }

        startMatch(games, hostName, guestName)
    }

    private fun startMatch(gamesToSet: Int, hostName: String, guestName: String) {
        GlobalScope.launch {
            Repository.it?.insertMatch(
                started = Date().time,
                gamesToSet = gamesToSet,
                hostName = hostName,
                guestName = guestName
            )
        }
    }
}
