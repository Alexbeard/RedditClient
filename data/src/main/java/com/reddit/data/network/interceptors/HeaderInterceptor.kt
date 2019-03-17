package com.reddit.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor
    : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val newUrl = chain.request().url().newBuilder()
                .addQueryParameter(
                        "raw_json",
                        "1"
                )
                .build()
        val newRequest = chain.request().newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }

}