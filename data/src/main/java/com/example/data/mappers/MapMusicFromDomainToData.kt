package com.example.data.mappers

import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus
import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainSavedStatus
import javax.inject.Inject

class MapMusicFromDomainToData @Inject constructor(
    private val mapSavedStatus: Mapper<DomainSavedStatus, DataSavedStatus>
) : Mapper<DomainMusic, DataMusic> {
    override fun map(from: DomainMusic) = from.run {
        DataMusic(
            musicId = musicId,
            title = title,
            displayName = displayName,
            data = data,
            executor = executor,
            duration = duration,
            uri = uri,
            defaultIconId = defaultIconId,
            isFavorite = isFavorite,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}