package com.example.suitify.ui.screens.details.models

import androidx.compose.runtime.Stable
import com.example.suitify.models.Music

@Stable
data class MusicDetailsScreenModel(
    val music: Music,
    val playlistName: String,
    val isPlaying: Boolean
)