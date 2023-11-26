package com.example.suitify.mappers

import android.net.Uri
import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainSavedStatus
import com.example.suitify.models.Music
import com.example.suitify.models.SavedStatus
import javax.inject.Inject

class MapMusicFromDomainToUi @Inject constructor(
    private val mapSavedStatus: Mapper<DomainSavedStatus, SavedStatus>
) : Mapper<DomainMusic, Music> {
    override fun map(from: DomainMusic) = from.run {
        Music(
            musicId = musicId,
            title = title,
            displayName = displayName,
            data = data,
            executor = executor,
            uri = Uri.parse(uri),
            duration = duration,
            defaultIconId = defaultIconId,
            isPlaying = isPlaying,
            isFavorite = isFavorite,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}