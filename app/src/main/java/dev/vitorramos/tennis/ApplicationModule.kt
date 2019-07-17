package dev.vitorramos.tennis

import dagger.Module
import dagger.Provides
import dev.vitorramos.tennis.db.MatchModel
import dev.vitorramos.tennis.db.TheDatabase
import javax.inject.Singleton

@Module
class ApplicationModule(
    private val theRepository: TheRepository,
    private val matchModel: MatchModel,
    private val theDatabase: TheDatabase
) {
    @Provides
    @Singleton
    fun provideMatchModel(): MatchModel{
        return matchModel
    }
    @Provides
    @Singleton
    fun provideTheDatabase(): TheDatabase{
        return theDatabase
    }
    @Provides
    @Singleton
    fun provideTheRepository(): TheRepository{
        return theRepository
    }
}
