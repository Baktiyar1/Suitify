package com.example.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val MUSICS_TABLE_NAME = "musics_table"

@Entity(tableName = MUSICS_TABLE_NAME, indices = [Index("id"), Index("is_playing", "id")])
data class CacheMusic(
    @PrimaryKey @ColumnInfo(name = "id") val musicId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "executor") val executor: String,
    @ColumnInfo(name = "duration") val duration: Float,
    @ColumnInfo(name = "icon_id") val iconId: Int,
    @ColumnInfo(name = "is_playing") val isPlaying: Boolean,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "saved_status") val savedStatus: CacheSavedStatus,
)