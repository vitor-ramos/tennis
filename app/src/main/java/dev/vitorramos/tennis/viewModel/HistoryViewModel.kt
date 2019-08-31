package dev.vitorramos.tennis.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dev.vitorramos.tennis.repository.Repository

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    val matches by lazy { Repository.it?.getMatches() ?: MutableLiveData() }
}
