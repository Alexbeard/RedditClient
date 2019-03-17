package com.reddit.data.common.mappers

import java.util.*
import java.util.Collections.emptyList

class Mappers {
    companion object {

        fun <F, T> mapCollection(list: List<F>?, mapper: Mapper<F, T>): List<T> {
            return if (list == null) {
                emptyList()
            } else {
                val size = list.size
                val result = ArrayList<T>(size)

                for (i in 0 until size) {
                    val map = mapper.map(list[i])
                    if (map != null) {
                        result.add(map)
                    }
                }

                result
            }
        }
    }
}
