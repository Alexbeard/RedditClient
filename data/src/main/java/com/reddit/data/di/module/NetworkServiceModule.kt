package com.reddit.data.di.module

import com.reddit.data.di.qualifier.QueryQualifier
import com.reddit.data.network.service.RedditService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class NetworkServiceModule {

    @Provides
    @Singleton
    internal fun provideDiscoverService(
            @QueryQualifier
            retrofit: Retrofit
    ): RedditService {
        return retrofit.create(RedditService::class.java)
    }

}
