package com.reddit.domain.type


enum class ContentState {
    CONTENT, EMPTY, LOADING, ERROR;

    val isContent: Boolean get() = this == CONTENT

    val isLoading: Boolean get() = this == LOADING

    val isEmpty: Boolean get() = this == EMPTY

    val isError: Boolean get() = this == ERROR

}