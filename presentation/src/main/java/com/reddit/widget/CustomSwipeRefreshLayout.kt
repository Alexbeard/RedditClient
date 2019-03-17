package com.reddit.widget


import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet

import com.reddit.R

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class CustomSwipeRefreshLayout(context: Context, attrs: AttributeSet) : SwipeRefreshLayout(context, attrs) {

    private var disallowed: Boolean = false

    init {
        val accentColor: Int
        val a = context.obtainStyledAttributes(intArrayOf(R.attr.colorAccent))
        try {
            accentColor = a.getColor(0, 0)
        } finally {
            a.recycle()
        }
        setColorSchemeColors(accentColor)
    }

    override fun setRefreshing(refreshing: Boolean) {
        post { super.setRefreshing(refreshing) }
    }

    override fun canChildScrollUp(): Boolean {
        val canScrollUp = this.disallowed || super.canChildScrollUp()
        this.disallowed = false
        return canScrollUp
    }

    override fun requestDisallowInterceptTouchEvent(b: Boolean) {
        super.requestDisallowInterceptTouchEvent(b)
        this.disallowed = b
    }
}
