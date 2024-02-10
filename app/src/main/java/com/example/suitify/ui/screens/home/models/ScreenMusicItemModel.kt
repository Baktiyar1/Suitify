package com.example.suitify.ui.screens.home.models

import com.example.suitify.models.Music

data class ScreenMusicItemModel(
    val music: Music,
    val isPlaying: Boolean,
    val isNowPlayingMusic: Boolean
)