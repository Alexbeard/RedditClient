package com.reddit.data.utils

import java.util.*
import java.util.concurrent.TimeUnit


object TimeUtils {

    val times: MutableList<Long> = Arrays.asList(
            TimeUnit.DAYS.toMillis(365),
            TimeUnit.DAYS.toMillis(30),
            TimeUnit.DAYS.toMillis(1),
            TimeUnit.HOURS.toMillis(1),
            TimeUnit.MINUTES.toMillis(1),
            TimeUnit.SECONDS.toMillis(1))
    private val timesString = Arrays.asList("year", "month", "day", "hour", "minute", "second")

    fun toDuration(duration: Long): String {

        val res = StringBuffer()
        for (i in 0 until times.size) {
            val current = times[i]
            val temp = duration / current
            if (temp > 0) {
                res.append(temp).append(" ").append(timesString[i])
                        .append(
                                if (temp != 1L)
                                    "s"
                                else ""
                        ).append(" ago")
                break
            }
        }
        return if ("" == res.toString())
            "0 seconds ago"
        else
            res.toString()
    }
}