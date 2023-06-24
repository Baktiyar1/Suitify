package com.example.domain.models

data class DomainPlaylist(
    val id: String,
    val name: String,
    val iconId: Int,
    val musics: List<DomainMusic>,
    val isFavorite: Boolean,
    val isFromPlaying: Boolean,
    val savedStatus: DomainSavedStatus
)