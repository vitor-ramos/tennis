package dev.vitorramos.tennis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dev.vitorramos.tennis.matchScreen.MatchViewModel
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MatchViewModel::class)
    internal abstract fun matchViewModel(matchViewModel: MatchViewModel): ViewModel

    @Target(AnnotationTarget.FUNCTION)
    @Retention
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
}
