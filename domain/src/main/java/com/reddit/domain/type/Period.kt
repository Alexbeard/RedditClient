package com.reddit.domain.type

enum class Period {
    Hour, Day, Week, Month, Year, All;

    override fun toString(): String {
        return when (this) {
            Hour -> "hour"
            Day -> "day"
            Week -> "week"
            Month -> "month"
            Year -> "year"
            All -> "all"
        }
    }

    companion object {
        fun toEnum(enum: String): Period {
            return when (enum) {
                "hour" -> Hour
                "day" -> Day
                "week" -> Week
                "month" -> Month
                "year" -> Year
                "all" -> All
                else -> throw  IllegalArgumentException("Unkown period type = $enum")
            }
        }
    }

}