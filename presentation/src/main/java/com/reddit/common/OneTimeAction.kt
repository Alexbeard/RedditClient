package com.reddit.common

class OneTimeAction constructor(private val event: () -> Unit) {

    private var firstTime: Boolean = true

    fun invoke() {
        if (firstTime) {
            event.invoke()
        }
        firstTime = false
    }

    fun reset() {
        firstTime = true
    }
}