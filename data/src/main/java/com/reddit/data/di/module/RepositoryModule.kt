package com.reddit.data.di.module

import com.reddit.data.di.RepositoryKey
import com.reddit.data.feature.reddit.RedditRepositoryImpl
import com.reddit.domain.repository.RedditRepository
import com.reddit.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class RepositoryModule {

    @Binds
    @IntoMap
    @RepositoryKey(RedditRepository::class)
    abstract fun provideMovieRepository(repository: RedditRepositoryImpl): Repository

}
