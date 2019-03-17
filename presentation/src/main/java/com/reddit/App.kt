package com.reddit

import androidx.multidex.MultiDexApplication
import com.evernote.android.state.StateSaver
import com.reddit.di.AppComponent
import timber.log.Timber

abstract class App : MultiDexApplication() {

    abstract fun getInjector(): AppComponent

    override fun onCreate() {
        super.onCreate()

        getInjector().inject(this)

        initAndroidState()
        initTimber()
    }

    private fun initAndroidState() {
        StateSaver.setEnabledForAllActivitiesAndSupportFragments(this, true)
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

}
