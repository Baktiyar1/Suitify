package com.example.data.mappers

import com.example.data.models.DataMusic
import com.example.data.models.DataPlaylist
import com.example.data.models.DataSavedStatus
import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainPlaylist
import com.example.domain.models.DomainSavedStatus
import javax.inject.Inject

class MapPlaylistFromDataToDomain @Inject constructor(
    private val mapMusic: Mapper<DataMusic, DomainMusic>,
    private val mapSavedStatus: Mapper<DataSavedStatus, DomainSavedStatus>
) : Mapper<DataPlaylist, DomainPlaylist> {
    override fun map(from: DataPlaylist) = from.run {
        DomainPlaylist(
            id = id.toString(),
            name = name,
            iconId = iconId,
            musics = musics.map { mapMusic.map(it) },
            isFavorite = isFavorite,
            isFromPlaying = isFromPlaying,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}