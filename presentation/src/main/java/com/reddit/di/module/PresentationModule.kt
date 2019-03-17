package com.reddit.di.module

import com.reddit.common.error.DefaultErrorHandler
import com.reddit.common.error.ErrorHandler
import dagger.Module
import dagger.Provides


@Module
class PresentationModule {

    @Provides
    internal fun provideDefaultError(errorHandler: DefaultErrorHandler)
            : ErrorHandler = errorHandler
}
