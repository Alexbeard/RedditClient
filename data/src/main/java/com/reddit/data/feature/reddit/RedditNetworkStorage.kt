package com.reddit.data.feature.reddit

import com.reddit.data.common.mappers.Mappers
import com.reddit.data.network.mapper.RedditResponseMapper
import com.reddit.data.network.service.RedditService
import com.reddit.domain.model.Post
import com.reddit.domain.type.Period
import io.reactivex.Single
import javax.inject.Inject

class RedditNetworkStorage
@Inject constructor(
        private val redditService: RedditService,
        private val redditResponseMapper: RedditResponseMapper) {

    fun getTopList(
            count: Int,
            limit: Int,
            period: Period,
            after: String?
    ): Single<List<Post>> {
        return redditService.getTopList(count, limit, period.toString(), after)
                .map { model ->
                    model.data.children.forEach { child ->
                        child.data.after = model.data.after
                    }
                    model
                }
                .map { Mappers.mapCollection(it.data.children, redditResponseMapper) }
    }
}