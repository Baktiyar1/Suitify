package com.example.suitify.ui.screens.home.models

import com.example.suitify.models.Music

data class ScreenBottomMenuModel(
    val music: Music,
    val progress: Float,
    val isPlaying: Boolean
)