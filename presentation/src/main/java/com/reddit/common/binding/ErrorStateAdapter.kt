package com.reddit.common.binding

import androidx.databinding.BindingAdapter
import com.reddit.widget.StateErrorView

object ErrorStateAdapter {

    @JvmStatic
    @BindingAdapter(value = ["bind:onRetryClicked"])
    fun setListener(stateErrorView: StateErrorView,
                    onRetryClickListener: StateErrorView.OnRetryClickListener) {
        stateErrorView.setOnRetryClickListener(onRetryClickListener)
    }
}
