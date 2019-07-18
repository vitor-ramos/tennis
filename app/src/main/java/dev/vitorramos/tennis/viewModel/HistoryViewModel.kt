package dev.vitorramos.tennis.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.repository.TennisRepository

class HistoryViewModel : ViewModel() {
    var tennisRepository: TennisRepository? = null

    val matches by lazy { tennisRepository?.getMatches() ?: MutableLiveData() }
}
