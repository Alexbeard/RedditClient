package com.reddit.common

import android.content.Context
import android.os.Bundle
import com.evernote.android.state.StateSaver
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hannesdorfmann.fragmentargs.FragmentArgs

open class BaseBottomSheetDialog : BottomSheetDialogFragment() {

    override fun onAttach(context: Context?) {
        if (context is BaseActivity) {
            context.inject(this)
        }
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        FragmentArgs.inject(this)
        super.onCreate(savedInstanceState)
        StateSaver.restoreInstanceState(this, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        StateSaver.saveInstanceState(this, outState)
    }
}
