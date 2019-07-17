package dev.vitorramos.tennis

import dagger.Component

@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent
