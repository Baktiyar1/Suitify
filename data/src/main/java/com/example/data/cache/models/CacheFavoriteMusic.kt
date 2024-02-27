package com.example.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val FAVORITE_MUSICS_TABLE_NAME = "favorite_musics_table"

@Entity(tableName = FAVORITE_MUSICS_TABLE_NAME, indices = [Index("musicId")])
data class CacheFavoriteMusic(@PrimaryKey @ColumnInfo(name = "musicId") val musicId: Long)