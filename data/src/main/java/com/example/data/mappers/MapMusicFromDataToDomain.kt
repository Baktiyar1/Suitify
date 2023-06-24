package com.example.data.mappers

import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus
import com.example.domain.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainSavedStatus
import javax.inject.Inject

class MapMusicFromDataToDomain @Inject constructor(
    private val mapSavedStatus: Mapper<DataSavedStatus, DomainSavedStatus>
) : Mapper<DataMusic, DomainMusic> {
    override fun map(from: DataMusic) = from.run {
        DomainMusic(
            musicId = musicId.toString(),
            title = title,
            executor = executor,
            duration = duration,
            iconId = iconId,
            isPlaying = isPlaying,
            isFavorite = isFavorite,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}