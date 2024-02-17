package com.example.data.models

import com.example.data.R

data class DataMusic(
    val musicId: Long,
    val title: String,
    val displayName: String,
    val data: String,
    val executor: String,
    val duration: Int,
    val uri: String,
    val defaultIconId: Int = R.drawable.music_default,
    val isFavorite: Boolean = false,
    val isFromPlaying: Boolean = false,
    val savedStatus: DataSavedStatus = DataSavedStatus.NOT_SAVED
) {
//    companion object {
//        val unknown = DataMusic(
//            musicId = 0L,
//            title = String(),
//            displayName = String(),
//            data = String(),
//            executor = String(),
//            duration = 0,
//            uri = EMPTY_STRING,
//            defaultIconId = 0,
//            isFavorite = false,
//            isFromPlaying = false,
//            savedStatus = DataSavedStatus.NOT_SAVED,
//        )
//    }
}