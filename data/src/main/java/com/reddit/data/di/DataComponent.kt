package com.reddit.data.di

import android.content.Context
import com.reddit.data.di.module.ContextModule
import com.reddit.data.di.module.NetworkModule
import com.reddit.data.di.module.NetworkServiceModule
import com.reddit.data.di.module.RepositoryModule
import com.reddit.domain.repository.Repository
import dagger.Component
import javax.inject.Provider
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            ContextModule::class,
            NetworkModule::class,
            NetworkServiceModule::class,
            RepositoryModule::class
        ]
)
interface DataComponent {

    fun getRepositories(): Map<Class<out Repository>, @JvmSuppressWildcards Provider<Repository>>

    class Initializer private constructor() {
        companion object {

            fun init(context: Context): DataComponent {
                return DaggerDataComponent.builder()
                        .contextModule(ContextModule(context))
                        .build()
            }
        }
    }
}