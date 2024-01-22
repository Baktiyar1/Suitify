package com.example.domain.models

data class DomainMusic(
    val musicId: Long,
    val title: String,
    val displayName: String,
    val data: String,
    val executor: String,
    val duration: Int,
    val uri: String,
    val defaultIconId: Int = 0,
    val isFavorite: Boolean = false,
    val isFromPlaying: Boolean = false,
    val savedStatus: DomainSavedStatus = DomainSavedStatus.NOT_SAVED,
)