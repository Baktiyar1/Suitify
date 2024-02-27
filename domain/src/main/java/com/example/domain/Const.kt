package com.example.domain

import java.util.concurrent.TimeUnit

const val UNKNOWN_TEXT = "Unknown"
const val DEFAULT_LONG = 0L
const val DEFAULT_FLOAT = 0f
const val DEFAULT_INT = 0
const val DEFAULT_BOOLEAN = false

fun formatDuration(duration: Long): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
    val totalSeconds = TimeUnit.MILLISECONDS.toSeconds(duration)
    val remainingSeconds = totalSeconds - (60 * minutes)
    return String.format("%02d:%02d", minutes, remainingSeconds)
}