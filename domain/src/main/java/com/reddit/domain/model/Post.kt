package com.reddit.domain.model

data class Post(
        val id: String,
        val title: String,
        val score: Int,
        val commentsCount: Int,
        val imgUrl: Image?,
        val date: String,
        val subredditNamePrefixed: String,
        val authorName: String,
        val after: String?,
        var permalink: String
)