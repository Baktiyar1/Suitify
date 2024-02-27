package com.example.suitify.ui.screens.details.models

import androidx.compose.runtime.Stable
import com.example.suitify.models.Music

@Stable
data class MusicPlayerScreenModel(
    val music: Music,
    val progress: Float,
    val progressText: String,
    val maxDurationText: String,
    val isPlaying: Boolean
)