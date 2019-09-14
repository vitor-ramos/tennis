package dev.vitorramos.tennis.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    val matches by lazy { Repository.it?.getMatches() ?: MutableLiveData() }

    fun undoDeletion(matchEntity: MatchEntity) {
        GlobalScope.launch {
            Repository.it?.undoDeletion(matchEntity)
        }
    }
}
