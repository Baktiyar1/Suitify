package com.example.suitify.models

import android.net.Uri
import com.example.suitify.R

data class Music(
    val musicId: Long,
    val title: String,
    val displayName: String,
    val data: String,
    val executor: String,
    val duration: Int,
    val uri: Uri,
    val defaultIconId: Int = R.drawable.music_default,
    val savedStatus: SavedStatus = SavedStatus.NOT_SAVED,
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
            musicId = Long.MIN_VALUE,
            title = String(),
            displayName = String(),
            data = String(),
            executor = String(),
            duration = Int.MIN_VALUE,
            uri = Uri.EMPTY,
            savedStatus = SavedStatus.NOT_SAVED
        )
    }

}