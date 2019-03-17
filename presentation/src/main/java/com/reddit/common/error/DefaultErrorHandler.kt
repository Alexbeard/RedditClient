package com.reddit.common.error

import android.content.Context
import com.reddit.domain.exception.APIException
import timber.log.Timber
import javax.inject.Inject

class DefaultErrorHandler
@Inject
constructor(var context: Context) : ErrorHandler {

    override fun handleError(throwable: Throwable) {
        handleError(throwable, null)
    }

    override fun handleError(throwable: Throwable, errorView: ((message: String) -> Unit)?) {
        Timber.e(throwable)

        if (errorView != null) {
            val message: String?

            if (throwable is APIException) {
                message = throwable.message
            } else {
                // todo ass string resource
                message = "Server Error"
            }
            if (message != null) errorView.invoke(message)
        }
    }

}
