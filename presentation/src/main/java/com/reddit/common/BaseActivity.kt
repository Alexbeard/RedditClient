package com.reddit.common

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.f2prateek.dart.Dart
import com.reddit.BuildConfig

abstract class BaseActivity : AppCompatActivity() {

    abstract fun inject(fragment: Fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        if (BuildConfig.DEBUG) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        }
        Dart.inject(this)
        super.onCreate(savedInstanceState)
    }

}