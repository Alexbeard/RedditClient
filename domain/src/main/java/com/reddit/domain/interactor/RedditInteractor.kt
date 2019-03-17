package com.reddit.domain.interactor

import com.reddit.domain.model.PageBundle
import com.reddit.domain.model.Post
import com.reddit.domain.repository.RedditRepository
import com.reddit.domain.type.Period
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class RedditInteractor
@Inject constructor(private val redditRepository: RedditRepository) {

    fun getTopList(period: Period): Observable<PageBundle<Post>> {
        return redditRepository.getTopList(period)
    }

    fun fetchNextList(period: Period): Completable {
        return redditRepository.fetchNextList(period)
    }

    fun saveFilter(period: Period): Completable {
        return redditRepository.saveFilter(period)
    }

    fun observeFilter(): Observable<Period> {
        return redditRepository.observeFilter()
    }

    fun refresh(period: Period): Completable {
        return redditRepository.refresh(period)
    }

}