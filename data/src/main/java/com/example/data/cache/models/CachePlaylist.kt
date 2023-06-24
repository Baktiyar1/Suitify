package com.example.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val PLAYLIST_TABLE_NAME = "playlist_table"

@Entity(tableName = PLAYLIST_TABLE_NAME, indices = [Index("id"), Index("is_playing", "id")])
data class CachePlaylist(
    @PrimaryKey @ColumnInfo(name = "savedStatus") val id: String,
    @ColumnInfo(name = "savedStatus") val name: String,
    @ColumnInfo(name = "savedStatus") val iconId: Int,
    @ColumnInfo(name = "savedStatus") val musics: List<CacheMusic>,
    @ColumnInfo(name = "savedStatus") val isFavorite: Boolean,
    @ColumnInfo(name = "savedStatus") val isFromPlaying: Boolean,
    @ColumnInfo(name = "savedStatus") val savedStatus: CacheSavedStatus,
)