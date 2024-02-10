package com.example.data.cache.db

import android.net.Uri
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CachePlaylist
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(entities = [CacheMusic::class, CachePlaylist::class], version = 3, exportSchema = true)
@TypeConverters(AppDatabase.DatabaseConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMusicsDao(): MusicDao

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
        fun convertFromStringToUri(data: String?): Uri = Uri.parse(data) ?: Uri.EMPTY

        @TypeConverter
        fun convertFromUriToString(uri: Uri): String = uri.toString()

    }
}