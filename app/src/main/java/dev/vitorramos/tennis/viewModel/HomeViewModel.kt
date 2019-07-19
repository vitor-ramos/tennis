package dev.vitorramos.tennis.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.repository.Repository

class HomeViewModel : ViewModel() {
    val onGoingMatch = MutableLiveData<Boolean>().apply {
        postValue(false)
    }

    fun currentMatch(matchId: Long): LiveData<MatchEntity?> {
        onGoingMatch.postValue(true)
        return Repository.it?.getMatch(matchId) ?: MutableLiveData()
    }
}
