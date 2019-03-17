package com.reddit.data.di.qualifier

import javax.inject.Qualifier


@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class QueryQualifier(val value: String = "")
