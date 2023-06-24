package com.example.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CachePlaylist
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//@Database(entities = [CacheMusic::class, CachePlaylist::class], version = 1, exportSchema = true)
//@TypeConverters(AppDatabase.DatabaseConverter::class)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun getMusicsDao(): MusicDao
//
//    abstract fun getPlaylistsDao(): PlaylistDao
//
//    class DatabaseConverter {
//
//        @TypeConverter
//        fun convertFromMusicListToString(countryLang: List<CacheMusic?>?): String? {
//            if (countryLang == null) return null
//            return Gson().toJson(countryLang, object : TypeToken<List<CacheMusic?>?>() {}.type)
//        }
//
//        @TypeConverter
//        fun convertFromStringToMusicList(countryLang: List<CacheMusic?>?): String? {
//            if (countryLang == null) return null
//            return Gson().toJson(countryLang, object : TypeToken<List<CacheMusic?>?>() {}.type)
//        }
//
//    }
//}