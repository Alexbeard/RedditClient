package com.reddit.data.di

import com.reddit.domain.repository.Repository
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class RepositoryKey(val value: KClass<out Repository>)