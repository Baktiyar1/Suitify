package com.example.suitify.models

import com.example.suitify.R

data class Playlist(
    val id: String? = null,
    val name: String,
    val iconId: Int = R.drawable.default_playlist,
    val musics: List<Music>,
    var isFavorite: Boolean = false,
    val savedStatus: SavedStatus = SavedStatus.NOT_SAVED
) {
    fun doesMatchSearchQuery(query: String): Boolean = name.contains(query, ignoreCase = true)
}