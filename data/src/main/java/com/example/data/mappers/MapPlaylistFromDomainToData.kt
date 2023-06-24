package com.example.data.mappers

import com.example.data.models.DataMusic
import com.example.data.models.DataPlaylist
import com.example.data.models.DataSavedStatus
import com.example.domain.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainPlaylist
import com.example.domain.models.DomainSavedStatus
import javax.inject.Inject

class MapPlaylistFromDomainToData @Inject constructor(
    private val mapMusic: Mapper<DomainMusic, DataMusic>,
    private val mapSavedStatus: Mapper<DomainSavedStatus, DataSavedStatus>
) :
    Mapper<DomainPlaylist, DataPlaylist> {
    override fun map(from: DomainPlaylist) = from.run {
        DataPlaylist(
            id = id,
            name = name,
            iconId = iconId,
            musics = musics.map { mapMusic.map(it) },
            isFavorite = isFavorite,
            isFromPlaying = isFromPlaying,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}