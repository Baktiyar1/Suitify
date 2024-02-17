package com.example.suitify.ui.screens.home.models

import androidx.compose.runtime.Stable
import com.example.suitify.models.Music
import com.example.suitify.models.Playlist

@Stable
data class ScreenSearchModel(
    val musics: List<Music>,
    val playlists: List<Playlist>,
    val nowPlayingMusicId: Long,
    val isPlaying: Boolean,
    val isVisibleSearch: Boolean,
    val isVisibleCategory: Boolean,
    val searchText: String
)