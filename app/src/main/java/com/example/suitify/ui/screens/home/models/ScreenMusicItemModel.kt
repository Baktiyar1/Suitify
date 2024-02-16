package com.example.suitify.ui.screens.home.models

import com.example.suitify.models.Music

data class ScreenMusicItemModel(
    val music: Music,
    val nawPlayingMusicId: Long,
    val isPlaying: Boolean,
)