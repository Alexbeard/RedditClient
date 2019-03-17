package com.reddit.data.network.factory

import com.google.gson.GsonBuilder

object NetworkFactory {

    fun createGson() = GsonBuilder().create()!!

}