package com.example.suitify.ui.screens.home.models

import androidx.compose.runtime.Stable
import com.example.suitify.models.Music

@Stable
data class ScreenMusicItemListModel(
    val musics: List<Music>,
    val isPlaying: Boolean,
    val nowPlayingMusicId: Long
)