package dev.vitorramos.tennis

import android.app.Application
import androidx.room.Room
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import dev.vitorramos.tennis.model.MatchModel
import dev.vitorramos.tennis.repository.Repository
import net.danlew.android.joda.JodaTimeAndroid

@Suppress("unused") // Used in Manifest.xml
class TennisApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AppCenter.start(
            this, BuildConfig.appsecret, Analytics::class.java, Crashes::class.java
        )

        JodaTimeAndroid.init(this)

        val db = Room.databaseBuilder(
            applicationContext,
            TennisDatabase::class.java,
            DATABASE_NAME
        ).build()
        Repository.init(MatchModel(db))
    }

    companion object {
        private const val DATABASE_NAME = "tennis-database"
    }
}
