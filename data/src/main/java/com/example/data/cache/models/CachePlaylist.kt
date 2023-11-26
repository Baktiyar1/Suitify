package com.example.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val PLAYLIST_TABLE_NAME = "playlist_table"

@Entity(tableName = PLAYLIST_TABLE_NAME, indices = [Index("id"), Index("isFromPlaying", "id")])
data class CachePlaylist(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "iconId") val iconId: Int,
    @ColumnInfo(name = "musics") val musics: List<CacheMusic>,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean,
    @ColumnInfo(name = "isFromPlaying") val isFromPlaying: Boolean,
    @ColumnInfo(name = "savedStatus") val savedStatus: CacheSavedStatus,
)