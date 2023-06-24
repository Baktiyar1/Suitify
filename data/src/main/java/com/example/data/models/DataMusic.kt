package com.example.data.models

import com.example.data.R

data class DataMusic(
    val musicId: String? = null,
    val title: String,
    val executor: String,
    val duration: Float,
    val iconId: Int = R.drawable.music_default,
    val isPlaying: Boolean = false,
    val isFavorite: Boolean = false,
    val isFromPlaying: Boolean = false,
    val savedStatus: DataSavedStatus = DataSavedStatus.NOT_SAVED
)