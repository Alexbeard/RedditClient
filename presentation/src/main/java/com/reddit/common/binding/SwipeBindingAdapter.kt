package com.reddit.common.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


object SwipeBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["bind:onRefresh"], requireAll = false)
    fun onRefresh(swipe: SwipeRefreshLayout, listener: SwipeRefreshLayout.OnRefreshListener) {
        swipe.setOnRefreshListener(listener)
    }

    @JvmStatic
    @BindingAdapter(value = ["bind:enabled"], requireAll = false)
    fun enable(swipe: SwipeRefreshLayout, enable: Boolean) {
        swipe.isEnabled = enable
    }

    @JvmStatic
    @BindingAdapter(value = ["bind:refreshing"], requireAll = false)
    fun refreshing(swipe: SwipeRefreshLayout, refreshing: Boolean) {
        swipe.isRefreshing = refreshing
    }
}
