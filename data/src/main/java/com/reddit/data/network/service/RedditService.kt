package com.reddit.data.network.service

import com.reddit.data.network.model.Model
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET("r/all/top.json")
    fun getTopList(
            @Query("count") count: Int,
            @Query("limit") limit: Int,
            @Query("t") period: String,
            @Query("after") after: String?
    ): Single<Model>

}