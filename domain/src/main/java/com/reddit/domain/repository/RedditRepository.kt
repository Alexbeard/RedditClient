package com.reddit.domain.repository

import com.reddit.domain.model.PageBundle
import com.reddit.domain.model.Post
import com.reddit.domain.type.Period
import io.reactivex.Completable
import io.reactivex.Observable

interface RedditRepository : Repository {

    fun getTopList(period: Period): Observable<PageBundle<Post>>

    fun fetchNextList(period: Period): Completable

    fun saveFilter(period: Period): Completable

    fun observeFilter(): Observable<Period>

    fun refresh(period: Period): Completable
}