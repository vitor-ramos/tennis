package dev.vitorramos.tennis.viewModel

import android.app.Application
import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_POSITIVE
import androidx.annotation.IdRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.vitorramos.tennis.Match
import dev.vitorramos.tennis.Match.WhichPlayer
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchViewModel(application: Application) : AndroidViewModel(application) {
    var matchId: Long? = null

    // TODO: null safety
    val currentMatch by lazy { Repository.it?.getMatch(matchId!!) ?: MutableLiveData() }
    private val finishLiveData = MutableLiveData<Boolean>()
    fun finishObservable(): LiveData<Boolean> = finishLiveData
    private val dialogLiveData = MutableLiveData<Boolean>()
    fun dialogObservable(): LiveData<Boolean> = dialogLiveData

    private fun addPoint(whichPlayer: WhichPlayer) {
        if (currentMatch.value != null) {
            val updatedMatch = Match.addPoint(whichPlayer, currentMatch.value!!)
            GlobalScope.launch {
                Repository.it?.updateMatch(updatedMatch)
            }
        }
    }

    private fun deleteMatch() {
        if (matchId != null) {
            GlobalScope.launch {
                Repository.it?.deleteMatch(matchId!!)
            }
        }
    }

    fun onOptionItemSelected(@IdRes id: Int): Boolean {
        return if (id == R.id.menu_match_delete && currentMatch.value != null) {
            dialogLiveData.postValue(true)
            true
        } else false
    }

    fun onClick(@IdRes id: Int) {
        when (id) {
            R.id.cl_match_host -> addPoint(WhichPlayer.HOST)
            R.id.cl_match_guest -> addPoint(WhichPlayer.GUEST)
        }
    }

    val onDialogClickListener = { dialog: DialogInterface, which: Int ->
        if (which == BUTTON_POSITIVE) {
            finishLiveData.postValue(true)
            deleteMatch()
        }
        dialog.dismiss()
    }
}
