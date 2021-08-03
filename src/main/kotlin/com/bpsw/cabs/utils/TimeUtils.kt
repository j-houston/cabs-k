package com.bpsw.cabs.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeUtils {
    companion object {
        val MINUTES_PER_DAY: Int = 24 * 60

        fun getCurrentDatetimeFormatted(): String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            return current.format(formatter)
        }
    }
}
