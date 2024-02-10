package com.example.data.repository

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import com.example.data.cache.source.music.MusicCacheDataSource
import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus
import com.example.core.APPLICATION_ERROR_LOG_KEY
import com.example.core.DEFAULT_ERROR_LOG_MESSAGE
import com.example.core.DEFAULT_ERROR_MESSAGE
import com.example.domain.base.Mapper
import com.example.domain.common.Result
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainSavedStatus
import com.example.domain.repositories.MusicRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val cacheDataSource: MusicCacheDataSource,
    private val mapMusicDataToDomain: Mapper<DataMusic, DomainMusic>,
    private val mapMusicListDataToDomain: Mapper<List<DataMusic>, List<DomainMusic>>,
    private val mapMusicDomainToData: Mapper<DomainMusic, DataMusic>,
    private val mapSavedStatusDomainToData: Mapper<DomainSavedStatus, DataSavedStatus>
) : MusicRepository {

    override fun fletchAllLocalMusic(): Result<List<DomainMusic>> = try {
        val musics = mutableListOf<DataMusic>()
        val cursor: Cursor? =
            context.contentResolver.query(URI, PROJECTION, SELECTION, SELECTION_ARGS, SORT_ORDER)
        cursor?.use {
            if (it.count == 0) Log.e(
                APPLICATION_ERROR_LOG_KEY,
                "MusicRepositoryImpl fletchAllLocalMusic: $DEFAULT_ERROR_LOG_MESSAGE"
            )
            else while (it.moveToNext()) musics.add(
                DataMusic(
                    musicId = getCursorLong(it),
                    title = getCursorString(it, TITLE),
                    displayName = getCursorString(it, DISPLAY_NAME),
                    executor = getCursorString(it, ARTIST),
                    duration = getCursorInt(it),
                    data = getCursorString(it, DATA),
                    uri = ContentUris.withAppendedId(URI, getCursorLong(it))
                )
            )
        }
        Result.Success(data = mapMusicListDataToDomain.map(musics))
    } catch (e: Exception) {
        Log.e(
            APPLICATION_ERROR_LOG_KEY,
            "MusicRepositoryImpl fletchAllLocalMusic: ${e.message ?: DEFAULT_ERROR_MESSAGE}"
        )
        Result.Error(DEFAULT_ERROR_MESSAGE)
    }

    override suspend fun fetchAllMusicsObservable(): Result<List<DomainMusic>> = try {
        Result.Success(data = mapMusicListDataToDomain.map(cacheDataSource.fetchAllMusics()))
    } catch (e: Exception) {
        Log.e(
            APPLICATION_ERROR_LOG_KEY,
            "MusicRepositoryImpl fetchAllMusicsObservable: ${e.message ?: DEFAULT_ERROR_MESSAGE}"
        )
        Result.Error(DEFAULT_ERROR_MESSAGE)
    }

    override fun fetchMusicObservable(musicId: String): Result<DomainMusic> = try {
        Result.Success(data = mapMusicDataToDomain.map(cacheDataSource.fetchMusic(musicId = musicId)))
    } catch (e: Exception) {
        Log.e(
            APPLICATION_ERROR_LOG_KEY,
            "MusicRepositoryImpl fetchMusicObservable: ${e.message ?: DEFAULT_ERROR_MESSAGE}"
        )
        Result.Error(DEFAULT_ERROR_MESSAGE)
    }

    override suspend fun fetchMusicFromId(musicId: String): Result<DomainMusic> = try {
        Result.Success(mapMusicDataToDomain.map(cacheDataSource.fetchMusicFromId(musicId = musicId)))
    } catch (e: Exception) {
        Log.e(
            APPLICATION_ERROR_LOG_KEY,
            "MusicRepositoryImpl fetchMusicFromId: ${e.message ?: DEFAULT_ERROR_MESSAGE}"
        )
        Result.Error(DEFAULT_ERROR_MESSAGE)
    }


    override suspend fun saveMusics(musics: List<DomainMusic>) =
        cacheDataSource.saveMusics(musics = musics.map { music -> mapMusicDomainToData.map(music) })

    override suspend fun addNewMusic(music: DomainMusic) =
        cacheDataSource.addNewMusic(music = mapMusicDomainToData.map(music))

    override suspend fun deleteMusic(id: String) = cacheDataSource.deleteMusic(id = id)

    override suspend fun clearTable() = cacheDataSource.clearTable()

    override suspend fun updateTitle(id: String, title: String) =
        cacheDataSource.updateTitle(id = id, title = title)

    override suspend fun updatePoster(id: String, iconId: Int) =
        cacheDataSource.updatePoster(id = id, iconId = iconId)

    override suspend fun updateMusicSavedStatus(id: String, savedStatus: DomainSavedStatus) =
        cacheDataSource.updateMusicSavedStatus(
            savedStatus = mapSavedStatusDomainToData.map(savedStatus), id = id
        )

    override suspend fun updateExecutor(id: String, executor: String) =
        cacheDataSource.updateExecutor(id = id, executor = executor)

    private fun getCursorString(cursor: Cursor, value: String) =
        cursor.getString(cursor.getColumnIndexOrThrow(value))

    private fun getCursorLong(cursor: Cursor) = cursor.getLong(cursor.getColumnIndexOrThrow(ID))

    private fun getCursorInt(cursor: Cursor) = cursor.getInt(cursor.getColumnIndexOrThrow(DURATION))

    private companion object {
        private val URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        private const val SELECTION = "${MediaStore.Audio.AudioColumns.IS_MUSIC} = ?"
        private val SELECTION_ARGS = arrayOf("1")
        private const val DISPLAY_NAME = MediaStore.Audio.AudioColumns.DISPLAY_NAME
        private const val SORT_ORDER = "$DISPLAY_NAME ASC"
        private const val ID = MediaStore.Audio.AudioColumns._ID
        private const val ARTIST = MediaStore.Audio.AudioColumns.ARTIST
        private const val DATA = MediaStore.Audio.AudioColumns.DATA
        private const val DURATION = MediaStore.Audio.AudioColumns.DURATION
        private const val TITLE = MediaStore.Audio.AudioColumns.TITLE
        private val PROJECTION = arrayOf(DISPLAY_NAME, ID, ARTIST, DATA, DURATION, TITLE)
    }
}