package com.example.suitify.models

import com.example.suitify.R

data class Music(
    val musicId: String? = null,
    val title: String,
    val executor: String,
    val duration: Float,
    val iconId: Int = R.drawable.music_default,
    var isPlaying: Boolean = false,
    var isFavorite: Boolean = false,
    var isFromPlaying: Boolean = false,
) {

    fun doesMatchSearchQuery(query: String): Boolean = listOf(
        "$title$executor",
        "$title $executor",
        "${title.first()}${executor.first()}",
        "${title.first()} ${executor.first()}",
    ).any { it.contains(query, ignoreCase = true) }

    companion object {
        fun unknown() = Music(
            musicId = String(),
            title = String(),
            executor = String(),
            duration = Float.MIN_VALUE,
        )
    }

}