package com.reddit.injector

import com.reddit.App
import com.reddit.di.AppComponent
import com.reddit.data.di.DataComponent

class InjectorApplication : App() {

    private val appComponent: AppComponent by lazy {
        val dataComponent = DataComponent.Initializer
            .init(this)

        AppComponent.Initializer
            .init(this, dataComponent.getRepositories())
    }

    override fun getInjector(): AppComponent {
        return appComponent
    }

}