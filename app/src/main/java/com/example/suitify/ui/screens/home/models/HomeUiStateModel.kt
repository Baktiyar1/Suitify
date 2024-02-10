package com.example.suitify.ui.screens.home.models

import com.example.suitify.models.Music
import com.example.suitify.models.Playlist

data class HomeUiStateModel(
    val musics: List<Music>,
    val playlists: List<Playlist>,
    val music: Music,
    val progress: Float,
    val isPlaying: Boolean,
    val isVisibleSearch: Boolean,
    val isVisibleCategory: Boolean,
    val searchText: String,
)