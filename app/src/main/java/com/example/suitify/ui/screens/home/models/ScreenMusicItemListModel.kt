package com.example.suitify.ui.screens.home.models

import com.example.suitify.models.Music

data class ScreenMusicItemListModel(
    val musics: List<Music>,
    val isPlaying: Boolean,
    val nowPlayingMusicId: Long
)