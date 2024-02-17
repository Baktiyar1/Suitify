package com.example.data.cache.source.local

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.WorkerThread
import com.example.data.cache.models.CacheMusic
import com.example.core.APPLICATION_ERROR_LOG_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContentResolverHelper @Inject constructor(@ApplicationContext val context: Context) {

    private var mCursor: Cursor? = null

    @WorkerThread
    fun getAudioData(): List<CacheMusic> = getCursorData()

    private fun getCursorData(): MutableList<CacheMusic> {
        val musics = mutableListOf<CacheMusic>()

        mCursor = context.contentResolver.query(
            EXTERNAL_CONTENT_URI,
            PROJECTION,
            SELECTION_CLAUSE,
            SELECTION_ARGS,
            SORT_ORDER
        )

        mCursor?.use {
            if (it.count == 0) Log.e(
                APPLICATION_ERROR_LOG_KEY, "ContentResolverHelper getCursorData: Cursor is Empty"
            ) else while (it.moveToNext()) musics.add(
                CacheMusic(
                    displayName = getCursorString(it, DISPLAY_NAME),
                    musicId = getCursorLong(it),
                    executor = getCursorString(it, ARTIST),
                    data = getCursorString(it, DATA),
                    duration = getCursorInt(it),
                    title = getCursorString(it, TITLE),
                    uri = ContentUris.withAppendedId(EXTERNAL_CONTENT_URI, getCursorLong(it)).toString(),
                )
            )
        }
        return musics
    }

    private fun getCursorString(cursor: Cursor, value: String) =
        cursor.getString(cursor.getColumnIndexOrThrow(value))

    private fun getCursorLong(cursor: Cursor) = cursor.getLong(cursor.getColumnIndexOrThrow(ID))

    private fun getCursorInt(cursor: Cursor) = cursor.getInt(cursor.getColumnIndexOrThrow(DURATION))

    companion object {
        private const val DISPLAY_NAME = MediaStore.Audio.AudioColumns.DISPLAY_NAME
        private const val ID = MediaStore.Audio.AudioColumns._ID
        private const val ARTIST = MediaStore.Audio.AudioColumns.ARTIST
        private const val DATA = MediaStore.Audio.AudioColumns.DATA
        private const val DURATION = MediaStore.Audio.AudioColumns.DURATION
        private const val TITLE = MediaStore.Audio.AudioColumns.TITLE
        private const val IS_MUSIC = MediaStore.Audio.AudioColumns.IS_MUSIC
        private val EXTERNAL_CONTENT_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        private const val SELECTION_CLAUSE: String = "$IS_MUSIC = ?"
        private val SELECTION_ARGS = arrayOf("1")
        private const val SORT_ORDER = "$DISPLAY_NAME ASC"
        private val PROJECTION by lazy { arrayOf(DISPLAY_NAME, ID, ARTIST, DATA, DURATION, TITLE) }
    }
}