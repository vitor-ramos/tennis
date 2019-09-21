package dev.vitorramos.tennis.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.vitorramos.tennis.repository.Repository
import dev.vitorramos.tennis.view.MainAdapter.HistoryState
import dev.vitorramos.tennis.view.MainAdapter.HistoryState.STARTING

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val matches by lazy { Repository.it?.getMatches() ?: MutableLiveData() }

    private val state = MutableLiveData<HistoryState>()
    fun state(): LiveData<HistoryState> = state

    val onClickStart = {
        state.postValue(STARTING)
    }
}
