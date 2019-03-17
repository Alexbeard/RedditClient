package com.reddit.features.main

import android.app.Activity
import com.reddit.App
import com.reddit.di.AppComponent
import com.reddit.di.scope.ActivityScope
import com.reddit.features.main.filter.PeriodDialogFragment
import dagger.Component
import dagger.Module
import dagger.Provides


@ActivityScope
@Component(
        dependencies = [AppComponent::class],
        modules = [MainActivityComponent.ActivityModule::class]
)
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(periodDialogFragment: PeriodDialogFragment)

    @Module
    class ActivityModule(val activity: MainActivity) {

        @Provides
        internal fun provideMainActivity(): MainActivity = activity

        @Provides
        internal fun provideActivity(): Activity = activity
    }

    class Initializer private constructor() {
        companion object {
            fun init(activity: MainActivity): MainActivityComponent {
                return DaggerMainActivityComponent.builder()
                        .activityModule(ActivityModule(activity))
                        .appComponent((activity.application as App).getInjector())
                        .build()
            }
        }
    }

}
