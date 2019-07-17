package dev.vitorramos.tennis

import dagger.Component
import dev.vitorramos.tennis.db.MatchModel
import dev.vitorramos.tennis.db.TheDatabase

@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(matchModel: MatchModel)
    fun inject(theDatabase: TheDatabase)
    fun inject(viewModelFactory: ViewModelFactory)
}
