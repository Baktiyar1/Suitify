package com.example.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.cache.models.CacheFavoriteMusic
import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CachePlaylist
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(
    entities = [CacheMusic::class, CacheFavoriteMusic::class, CachePlaylist::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(AppDatabase.DatabaseConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMusicsDao(): MusicDao

    abstract fun getFavoritesDao(): FavoriteMusicDao

    abstract fun getPlaylistsDao(): PlaylistDao

    class DatabaseConverter {
        @TypeConverter
        fun convertFromStringToMusicList(data: String?): List<CacheMusic> =
            if (data == null) emptyList()
            else Gson().fromJson(data, object : TypeToken<List<CacheMusic>>() {}.type)

        @TypeConverter
        fun convertFromMusicListToString(myObjects: List<CacheMusic>): String =
            Gson().toJson(myObjects)

        @TypeConverter
        fun convertFromStringToFavoritesMusicList(data: String?): List<CacheFavoriteMusic> =
            if (data == null) emptyList()
            else Gson().fromJson(data, object : TypeToken<List<CacheMusic>>() {}.type)

        @TypeConverter
        fun convertFromFavoritesMusicListToString(myObjects: List<CacheFavoriteMusic>): String =
            Gson().toJson(myObjects)

    }
}