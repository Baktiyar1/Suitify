package com.example.domain

import java.util.concurrent.TimeUnit

const val UNKNOWN_TEXT = "Unknown"
const val DEFAULT_TIME_TEXT = "00:00"

const val DEFAULT_LONG = 0L
const val DEFAULT_FLOAT = 0f

fun formatDuration(duration: Long): String {
    val minute = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
    val seconds = minute - (minute * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES))
    return String.format("%02d:%02d", minute, seconds)
}