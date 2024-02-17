package com.example.suitify.models

import androidx.compose.runtime.Stable
import com.example.core.EMPTY_STRING
import com.example.core_ui.R
import javax.annotation.concurrent.Immutable

@Stable
data class Music(
    val musicId: Long,
    val title: String,
    val displayName: String,
    val data: String,
    val artist: String,
    val duration: Int,
    val uri: String,
    val defaultIconId: Int = R.drawable.music_default,
    val savedStatus: SavedStatus = SavedStatus.NOT_SAVED,
    var isFavorite: Boolean = false,
    var isFromPlaying: Boolean = false,
) {

    fun doesMatchSearchQuery(query: String): Boolean = listOf(
        "$title$artist",
        "$title $artist",
        "${title.first()}${artist.first()}",
        "${title.first()} ${artist.first()}",
    ).any { it.contains(query, ignoreCase = true) }

    companion object {
        fun unknown() = Music(
            musicId = Long.MIN_VALUE,
            title = String(),
            displayName = String(),
            data = String(),
            artist = String(),
            duration = Int.MIN_VALUE,
            uri = EMPTY_STRING,
            savedStatus = SavedStatus.NOT_SAVED
        )
    }

}