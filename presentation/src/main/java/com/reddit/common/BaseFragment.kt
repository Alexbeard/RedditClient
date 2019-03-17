package com.reddit.common

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hannesdorfmann.fragmentargs.FragmentArgs

open class BaseFragment : Fragment() {

    override fun onAttach(context: Context?) {
        if (context is BaseActivity) {
            context.inject(this)
        }
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        FragmentArgs.inject(this)
        super.onCreate(savedInstanceState)
    }

}
