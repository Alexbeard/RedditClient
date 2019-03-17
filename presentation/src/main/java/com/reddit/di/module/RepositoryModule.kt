package com.reddit.di.module

import com.reddit.domain.repository.RedditRepository
import com.reddit.domain.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class RepositoryModule(private val provides: Map<Class<out Repository>,
        @JvmSuppressWildcards Provider<Repository>>) {

    @Provides
    internal fun provideRedditRepository(): RedditRepository =
            provides.getValue(RedditRepository::class.java).get() as RedditRepository

}
