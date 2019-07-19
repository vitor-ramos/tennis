package dev.vitorramos.tennis.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.repository.Repository

class HistoryViewModel : ViewModel() {
    val matches by lazy { Repository.it?.getMatches() ?: MutableLiveData() }
}
