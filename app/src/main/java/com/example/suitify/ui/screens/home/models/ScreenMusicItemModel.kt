package com.example.suitify.ui.screens.home.models

import androidx.compose.runtime.Stable
import com.example.suitify.models.Music

@Stable
data class ScreenMusicItemModel(
    val music: Music,
    val nawPlayingMusicId: Long,
    val isPlaying: Boolean,
)