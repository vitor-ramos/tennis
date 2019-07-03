package dev.vitorramos.tennis.matchScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.db.entity.MatchEntity

class MatchViewModel : ViewModel() {
    fun getMatch(): LiveData<MatchEntity?> {
        return MutableLiveData()
    }

    fun addHostPoint() {
    }

    fun addGuestPoint() {
    }
}
