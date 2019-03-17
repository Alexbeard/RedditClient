package com.reddit.data.feature.reddit

import android.content.Context
import com.reddit.data.common.PreferenceStorage
import com.reddit.domain.type.Period
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class FilterPreference
@Inject internal constructor(context: Context) {

    private val preferenceStorage: PreferenceStorage =
            PreferenceStorage(context, PREFERENCE_NAME)

    private val filterObservable = preferenceStorage.observe(TRIGGER_FILTER) {
        val period = it.getString(FIELD_PERIOD, Period.All.toString())

        Period.toEnum(period)
    }

    fun saveFilter(period: Period): Completable {
        return preferenceStorage.update(TRIGGER_FILTER) {
            it.putString(
                    FIELD_PERIOD,
                    period.toString()
            )
        }
    }

    fun observeFilter(): Observable<Period> {
        return filterObservable
    }

    companion object {
        private const val TRIGGER_FILTER = "filter"
        private const val PREFERENCE_NAME = "period_preference"
        private const val FIELD_PERIOD = "period"
    }
}
