package com.example.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.R

const val MUSICS_TABLE_NAME = "musics_table"

@Entity(tableName = MUSICS_TABLE_NAME, indices = [Index("musicId")])
data class CacheMusic(
    @PrimaryKey @ColumnInfo(name = "musicId") val musicId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "executor") val executor: String,
    @ColumnInfo(name = "display_name") val displayName: String,
    @ColumnInfo(name = "data") val data: String,
    @ColumnInfo(name = "duration") val duration: Int,
    @ColumnInfo(name = "uri") val uri: String,
    @ColumnInfo(name = "default_icon_id") val defaultIconId: Int = R.drawable.music_default,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean = false,
    @ColumnInfo(name = "is_from_playing") val isFromPlaying: Boolean = false,
    @ColumnInfo(name = "saved_status") val savedStatus: CacheSavedStatus = CacheSavedStatus.NOT_SAVED,
)