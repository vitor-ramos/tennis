package dev.vitorramos.tennis

import android.app.Application
import dagger.internal.DaggerCollections

class TheApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}