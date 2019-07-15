package dev.vitorramos.tennis

import dagger.Component
import dev.vitorramos.tennis.db.MatchModel

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(matchModel: MatchModel)
}