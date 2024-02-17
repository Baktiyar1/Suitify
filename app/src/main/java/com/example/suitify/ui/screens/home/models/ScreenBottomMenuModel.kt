package com.example.suitify.ui.screens.home.models

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.suitify.models.Music

@Stable
data class ScreenBottomMenuModel(
    val music: Music,
    val progress: Float,
    val isPlaying: Boolean
)