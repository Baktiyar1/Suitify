package com.example.data.cache.models

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val MUSICS_TABLE_NAME = "musics_table"

@Entity(tableName = MUSICS_TABLE_NAME, indices = [Index("id"), Index("is_playing", "id")])
data class CacheMusic(
    @PrimaryKey @ColumnInfo(name = "id") val musicId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "executor") val executor: String,
    @ColumnInfo(name = "display_name") val displayName: String,
    @ColumnInfo(name = "data") val data: String,
    @ColumnInfo(name = "duration") val duration: Int,
    @ColumnInfo(name = "uri") val uri: Uri,
    @ColumnInfo(name = "default_icon_id") val defaultIconId: Int,
    @ColumnInfo(name = "is_playing") val isPlaying: Boolean,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "is_from_playing") val isFromPlaying: Boolean = false,
    @ColumnInfo(name = "saved_status") val savedStatus: CacheSavedStatus,
)