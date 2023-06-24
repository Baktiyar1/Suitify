package com.example.domain.models

data class DomainMusic(
    val musicId: String,
    val title: String,
    val executor: String,
    val duration: Float,
    val iconId: Int,
    val isPlaying: Boolean,
    val isFavorite: Boolean,
    val savedStatus: DomainSavedStatus
)