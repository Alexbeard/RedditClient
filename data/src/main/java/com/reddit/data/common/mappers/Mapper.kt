package com.reddit.data.common.mappers

interface Mapper<F, T> {
    fun map(value: F): T
}

