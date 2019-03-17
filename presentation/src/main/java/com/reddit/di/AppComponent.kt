package com.reddit.di

import androidx.lifecycle.ViewModelProvider
import com.reddit.App
import com.reddit.common.ViewModelFactory
import com.reddit.di.module.AppModule
import com.reddit.di.module.PresentationModule
import com.reddit.di.module.RepositoryModule
import com.reddit.di.module.ViewModelModule
import com.reddit.domain.repository.Repository
import dagger.Component
import javax.inject.Provider
import javax.inject.Singleton


@Component(
    modules = [
            AppModule::class,
            ViewModelModule::class,
            PresentationModule::class,
            RepositoryModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(app: App)

    fun getViewModelFactoryProvider(): ViewModelProvider.Factory

    class Initializer private constructor() {
        companion object {

            fun init(app: App, provides: Map<Class<out Repository>,
                    @JvmSuppressWildcards Provider<Repository>>): AppComponent {

                return DaggerAppComponent.builder()
                        .repositoryModule(RepositoryModule(provides))
                        .appModule(AppModule(app))
                        .build()
            }
        }
    }
}
