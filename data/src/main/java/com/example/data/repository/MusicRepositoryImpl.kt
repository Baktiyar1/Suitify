package com.example.data.repository

import android.content.Context
import android.provider.MediaStore
import com.example.data.models.DataMusic
import com.example.domain.models.DomainMusic
import com.example.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val context: Context,
//    private val cacheDataSource:
) : MusicRepository {
    override fun fletchAllMusic(): Flow<List<DomainMusic>> = flow {

        val tempAudioList = mutableListOf<DataMusic>()

        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, arrayOf(
                MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.ArtistColumns.ARTIST,
                MediaStore.Audio.AudioColumns.DURATION
            ), MediaStore.Audio.Media.DATA + " like ? ", arrayOf("%yourFolderName%"), null
        )

        if (cursor != null) while (cursor.moveToNext()) tempAudioList.add(
            DataMusic(
                title = cursor.getString(0).substring(cursor.getString(0).lastIndexOf("/") + 1),
                executor = cursor.getString(2),
                duration = cursor.getString(3).toFloat()
            )
        )

        cursor?.close()
    }
}