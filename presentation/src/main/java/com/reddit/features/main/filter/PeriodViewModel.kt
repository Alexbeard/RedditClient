package com.reddit.features.main.filter

import androidx.lifecycle.MutableLiveData
import com.reddit.common.BaseViewModel
import com.reddit.common.SingleLiveEvent
import com.reddit.common.error.DefaultErrorHandler
import com.reddit.domain.interactor.RedditInteractor
import com.reddit.domain.type.Period
import com.reddit.utils.RxDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PeriodViewModel
@Inject constructor(private val redditInteractor: RedditInteractor,
                    private val errorHandler: DefaultErrorHandler) : BaseViewModel() {

    var filter = MutableLiveData<Period>()
    var closeEvent = SingleLiveEvent<Unit>()

    init {
        observeFilter()
    }

    private fun observeFilter() {
        RxDisposable.manage(
                this, "observeFilter",
                redditInteractor.observeFilter()
                        .distinctUntilChanged()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            filter.value = it
                        }, { error -> errorHandler.handleError(error) })
        )
    }

    fun saveFilter(period: Period) {
        RxDisposable.manage(
                this, "saveFilter",
                redditInteractor.saveFilter(period)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({closeEvent.call()},
                                { error -> errorHandler.handleError(error) })
        )
    }
}