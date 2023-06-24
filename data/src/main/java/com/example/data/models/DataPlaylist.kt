package com.example.data.models

import com.example.data.R

data class DataPlaylist(
    val id: String? = null,
    val name: String,
    val iconId: Int = R.drawable.default_playlist,
    val musics: List<DataMusic>,
    val isFavorite: Boolean = false,
    val isFromPlaying:Boolean = false,
    val savedStatus: DataSavedStatus = DataSavedStatus.NOT_SAVED
)