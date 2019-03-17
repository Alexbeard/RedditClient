package com.reddit.data.network.mapper

import com.reddit.data.common.mappers.Mapper
import com.reddit.data.network.model.Children
import com.reddit.data.utils.TimeUtils
import com.reddit.domain.model.Image
import com.reddit.domain.model.Post
import java.util.*
import javax.inject.Inject

class RedditResponseMapper
@Inject constructor() : Mapper<Children, Post> {

    override fun map(value: Children): Post {
        return Post(
                value.data.id,
                value.data.title,
                value.data.score,
                value.data.numComments,
                getImage(value),
                getPostAgoDate(value),
                value.data.subredditNamePrefixed,
                value.data.author,
                value.data.after,
                value.data.permalink
        )
    }

    private fun getImage(children: Children): Image? {
        if (children.data.preview != null && !children.data.preview.images.isEmpty()) {
            return Image(
                    children.data.preview.images[0].source.url,
                    children.data.preview.images[0].source.width,
                    children.data.preview.images[0].source.height
            )
        }
        return null
    }

    private fun getPostAgoDate(children: Children): String {
        return TimeUtils.toDuration(Date().time - (children.data.createdUtc * 1000))
    }
}