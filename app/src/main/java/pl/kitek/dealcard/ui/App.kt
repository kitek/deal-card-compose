package pl.kitek.dealcard.ui

import android.app.Application
import pl.kitek.dealcard.BuildConfig
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
