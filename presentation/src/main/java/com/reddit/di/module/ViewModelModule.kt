package com.reddit.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reddit.common.ViewModelFactory
import com.reddit.di.ViewModelKey
import com.reddit.features.main.MainViewModel
import com.reddit.features.main.filter.PeriodViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PeriodViewModel::class)
    abstract fun bindPeriodViewModel(viewModel: PeriodViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}