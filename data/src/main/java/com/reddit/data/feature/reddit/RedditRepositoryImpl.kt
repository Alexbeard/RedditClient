package com.reddit.data.feature.reddit

import com.reddit.data.common.MemoryPagedListStorage
import com.reddit.data.common.cashe.CachePolicy
import com.reddit.domain.model.PageBundle
import com.reddit.domain.model.Post
import com.reddit.domain.repository.RedditRepository
import com.reddit.domain.type.Period
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditRepositoryImpl
@Inject constructor(private val redditNetworkStorage: RedditNetworkStorage,
                    private val filterPreference: FilterPreference) : RedditRepository {

    private val redditListStorage = MemoryPagedListStorage
            .Builder<Period, Post> { params ->
                redditNetworkStorage.getTopList(
                        params.page,
                        params.limit,
                        params.query,
                        params.entity?.after
                )
                        .map { MemoryPagedListStorage.FetchResult(it, it.isNotEmpty()) }
            }
            .cachePolicy(CachePolicy.create(1, TimeUnit.MINUTES))
            .capacity(1)
            .build()

    override fun getTopList(period: Period): Observable<PageBundle<Post>> {
        return redditListStorage[period]
    }

    override fun fetchNextList(period: Period): Completable {
        return redditListStorage.fetchNext(period)
    }

    override fun saveFilter(period: Period): Completable {
        return filterPreference.saveFilter(period)
    }

    override fun observeFilter(): Observable<Period> {
        return filterPreference.observeFilter()
    }

    override fun refresh(period: Period): Completable {
        return redditListStorage.refresh(period)
    }
}