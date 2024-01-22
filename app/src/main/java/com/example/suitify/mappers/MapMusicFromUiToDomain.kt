package com.example.suitify.mappers

import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainSavedStatus
import com.example.suitify.models.Music
import com.example.suitify.models.SavedStatus
import javax.inject.Inject

class MapMusicFromUiToDomain @Inject constructor(
    private val mapSavedStatus: Mapper<SavedStatus, DomainSavedStatus>
) : Mapper<Music, DomainMusic> {
    override fun map(from: Music) = from.run {
        DomainMusic(
            musicId = musicId,
            title = title,
            displayName = displayName,
            data = data,
            executor = artist,
            duration = duration,
            uri = uri.toString(),
            defaultIconId = defaultIconId,
            isPlaying = isPlaying,
            isFavorite = isFavorite,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}