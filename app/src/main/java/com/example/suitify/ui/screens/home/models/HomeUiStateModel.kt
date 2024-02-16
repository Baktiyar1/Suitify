package com.example.suitify.ui.screens.home.models

import androidx.compose.runtime.Stable
import com.example.suitify.models.Music
import com.example.suitify.models.Playlist

@Stable
data class HomeUiStateModel(
    val musics: List<Music>,
    val playlists: List<Playlist>,
    val musicId: Long,
    val progress: Float,
    val isPlaying: Boolean,
    val isVisibleSearch: Boolean,
    val isVisibleCategory: Boolean,
    val searchText: String,
)